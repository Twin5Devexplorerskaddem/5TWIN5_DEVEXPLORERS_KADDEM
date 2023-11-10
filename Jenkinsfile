pipeline {
    agent any
    environment {
        version = '1.0'
    }
    stages {
        stage('Git Checkout') {
            steps {
                script {
                    git branch: 'faresmekni-5TWIN5-G5-KADDEM', url: 'https://github.com/Twin5Devexplorerskaddem/5TWIN5_DEVEXPLORERS_KADDEM'
                }
            }
        }

        stage('Build') {
            steps {
                sh 'ls -ltr'
                sh 'mvn clean package'
            }
        }

        stage('SonarQube') {
            steps {
                script {
                    try {
                        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=fares'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        error "Error running SonarQube analysis: ${e.message}"
                    }
                }
            }
        }

                stage('Mockito Tests') {
            steps {
                    // Assurez-vous que vous êtes dans le répertoire du projet Maven
                    // Exécutez les tests Mockito avec Maven (assurez-vous d'avoir configuré les tests Mockito correctement)
                    sh 'mvn clean test -Pmockito-tests'
                }

            post {
                always {
                    // Archive les rapports de test Mockito JUnit
                    junit 'target/surefire-reports/TEST-tn.esprit.rh.achat.FournisseurServiceStaticTest.xml'

                }
            }
        }

   stage('Unit Tests') {
            steps {
                    // Assurez-vous que vous êtes dans le répertoire du projet Maven
                    // Exécutez les tests unitaires avec Maven
                    sh 'mvn test -Ptest-with-database'
                }

            post {
                always {
                    // Archive les rapports de test unitaires JUnit
                    junit 'target/surefire-reports/TEST-*'
                }
            }}


        stage ('NEXUS DEPLOY') {

       steps {
       sh 'mvn deploy -DskipTests'
             }
        }




        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t faresmekni-5twin5-g6 .'
                }
            }
        }

         stage('Docker Compose') {
                    steps {
                        sh 'docker compose up -d'
                    }
                }
         stage('Email notification') {
           steps{
                 mail bcc: '', body: 'the pipeline is done !', cc: '', from: '', replyTo: '', subject: 'Jenkins Email Notification', to: 'mohamedfares.mekni@esprit.tn'
           }
        }
        stage('Push image to Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerhubpwd')]) {
                   sh 'docker login -u faresmekni -p ${dockerhubpwd}'
                   }
                   sh 'docker push faresmekni-5twin5-g6'
                }
            }
        }
    }
}

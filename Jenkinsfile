pipeline {
    agent any // Specify the agent type here

    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling...'
                git branch: 'FarahHasnaoui-5TWIN5-G5-KADDEM', url: 'https://github.com/Twin5Devexplorerskaddem/5TWIN5_DEVEXPLORERS_KADDEM.git'
            }
        }
        stage('Maven Clean') {
            steps {
                echo 'Cleaning...'
                sh 'mvn clean'
            }
        }
        stage('Maven Compile') {
            steps {
                echo 'Compiling...'
                sh 'mvn compile'
            }
        }
         stage('Run Tests') {
            steps {
                echo 'Running JUnit tests...'
                sh 'mvn test' // Execute mvn test to run JUnit tests
            }
        }
          stage('Mvn SonarQube') {
            steps {
                echo 'Sonar...'
                    sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonarsonar"
                }
            }
        stage('Build Artifact') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh 'mvn package -DskipTests'
                }
            }
        }
         stage('Build Docker image'){
                    steps {
                            sh 'sudo docker build -t FarahHasnaoui-5twin5-G5-kaddem:latest -f DockerFile .'
                    }
                }

           stage('Deploy Artifact to Nexus') {
            steps {

                    sh 'mvn deploy -DskipTests=true'

            }
        }

         stage('Docker Compose Up') {
                   steps {
                        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                            sh 'sudo docker-compose up -d'
                        }
                }
            }


    }
}
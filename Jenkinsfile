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
                 sh 'docker build -t farahhasnaoui-5twin5-g5-kaddem:latest -f DockerFile .'
             }
         }

           stage('Deploy Artifact to Nexus') {
            steps {

                    sh 'mvn deploy -DskipTests=true'

            }
        }
        stage('Login to DockerHub') {
                            steps {
                                script {
                                withCredentials([usernamePassword(credentialsId: 'dockerHub', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                                    // Log in to Docker Hub
                                    sh "echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin"
                                }
                            }
                        }
                    }

         stage('Deploy Image to DockerHub') {
                             steps {
                                // Taguer l'image locale avec le nouveau tag pour Docker Hub
                                sh "docker tag farahhasnaoui-5twin5-g5-kaddem:latest farahhasnaoui/devopsimage:latest"
                                // Pousser l'image vers le Docker Hub
                                sh "docker push farahhasnaoui/devopsimage:latest"
                                }
                           }

         stage('Docker Compose Up') {
                   steps {
                        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                            sh 'docker-compose up -d'
                        }
                }
            }
         stage('Email notification') {
                  steps{
                      mail bcc: '', body: 'the pipeline is sucess !', cc: '', from: '', replyTo: '', subject: 'Jenkins Email Notification', to: 'farah.hasnaoui22@gmail.com'
                      }

             }

    }
}
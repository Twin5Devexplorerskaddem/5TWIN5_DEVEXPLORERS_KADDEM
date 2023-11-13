pipeline {
    agent any

    options {
        disableConcurrentBuilds()
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    checkout([
                        $class: 'GitSCM',
                        branches: [[name: 'OmarCharfi-5twin5-G5-KADDEM']],
                        userRemoteConfigs: [[url: 'https://github.com/Twin5Devexplorerskaddem/5TWIN5_DEVEXPLORERS_KADDEM.git']]
                    ])
                }
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Debug') {
            steps {
                sh 'ls -R'
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('sonarQube') {
            steps {
                sh 'mvn sonar:sonar'
            }
        }

        stage('Deploy to Nexus') {
            steps {
                sh 'mvn deploy'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t omarcharfi-5twin5-kaddem_app -f DockerFile .'
                }
            }
        }

        stage('Docker Compose Up') {
            steps {
                script {
                     sh 'docker-compose up -d'
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    
                    sh 'docker tag omarcharfi-5twin5-kaddem_app omarcharfi/omarcharfi-5twin5-kaddem_app:latest'
                    sh 'docker login -u omarcharfi -p Omar11117532!'
                    sh 'docker push omarcharfi/omarcharfi-5twin5-kaddem_app:latest'
                }
            }
        }

        stage('Email Notification') {
            steps {
                script {
                    emailext(
                        subject: "Build Notification - ${currentBuild.result}",
                        body: "Build ${currentBuild.result}: Job ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}<br>${BUILD_URL}",
                        recipientProviders: [[$class: 'CulpritsRecipientProvider']],
                        to: 'omarcharfii12@gmail.com',
                        replyTo: 'omar.charfi@esprit.tn',
                        mimeType: 'text/html'
                    )
                }
            }
        }
    }
}

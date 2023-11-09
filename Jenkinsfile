pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                script {
                    checkout([
                        $class: 'GitSCM',
                        branches: [[name: 'ChedlyKchaou-4TWIN5-G5-KADDEM']],
                        userRemoteConfigs: [[url: 'https://github.com/Twin5Devexplorerskaddem/5TWIN5_DEVEXPLORERS_KADDEM.git']]
                    ])
                }
            }
        }

        stage('Build') {
            steps {
                // Exécuter la construction Maven
                sh 'mvn clean install'
            }
        }

        stage('Unit Tests') {
            steps {
                // Exécuter les tests unitaires JUnit
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
                // Déployer l'artéfact sur Nexus Repository
                sh 'mvn deploy'
            }
        }
        
        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    sh 'docker tag kaddemprojectchedly_app_1:latest chedly1/chedlykchaou-5twin5-kaddem_app:latest'
                    sh 'docker login -u chedly1 -p chedly123456'
                    sh 'docker push chedly1/chedlykchaou-5twin5-kaddem_app:latest'
                }
            }
        }

        stage('Docker Compose Up') {
            steps {
                script {
                    // Exécutez docker-compose up
                    sh "docker-compose up -d"
                }
            }
        }
    }
}

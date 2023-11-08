pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Récupérer le code source depuis le dépôt Git privé
                git url: 'https://votre-git-private-url/repo.git'
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

        stage('Code Analysis') {
            steps {
                // Exécuter l'analyse de code avec SonarQube
                withSonarQube(env: 'Votre-SonarQube-Env') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Deploy to Nexus') {
            steps {
                // Déployer l'artéfact sur Nexus Repository
                sh 'mvn deploy'
            }
        }
        stage('Docker Compose Up') {
            steps {
                script {
                     sh "docker-compose up -d"
                }
            }
        }

}

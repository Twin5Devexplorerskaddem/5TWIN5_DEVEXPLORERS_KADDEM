pipeline {
    agent any // Specify the agent type here

    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling...'
                git branch: 'Zeinebhamdi-5twin5-G5-Kaddem', url: 'https://github.com/Twin5Devexplorerskaddem/5TWIN5_DEVEXPLORERS_KADDEM.git'
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
        stage('Artifact Construction') {
            steps {
                echo 'Building artifacts...'
                sh 'mvn package' // Or use the appropriate Maven command to build your artifacts
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
                    sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=zeineb"
                }
            }
         stage('Publish to Nexus') {
            steps {
                echo 'publishing...'
                    sh "mvn deploy"
                }
            }
            stage('Docker Image') {
            steps {
                sh 'docker build -t zeinebhamdi-5twin5-g6 .'
            }
        }
        stage('Docker Image Push') {
            steps {
                script {
                    sh 'echo "chnoft 50409924" | docker login --username "zeinebbb" --password-stdin'
                    sh 'docker tag zeinebhamdi-5twin5-g6 zeinebbb/devops:latest'
                    sh 'docker push zeinebbb/devops:latest'
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
            mail bcc: '', body: 'the pipeline is done !', cc: '', from: '', replyTo: '', subject: 'Jenkins Email Notification', to: 'zainebhamdi2013@gmail.com'
            }

            }
    }
}
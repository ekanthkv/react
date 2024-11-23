pipeline {
    agent any

    environment {
        DOCKER_USERNAME = 'ekanthkv' // Your Docker Hub username
        IMAGE_NAME = "react_react-app"
        DOCKER_HUB_REPO = "your-dockerhub-username/react-docker-app"
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'master', url: 'https://github.com/ekanthkv/react.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker-compose build'
                }
            }
        }

    

        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credential') {
                      sh  "docker tag react_react-app ${DOCKER_USERNAME}/react-docker-app:latest"
                       sh "docker push ${DOCKER_USERNAME}/react-docker-app:latest"
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    sh 'docker-compose down || true'
                    sh 'docker-compose up -d'
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed!'
        }
        success {
            echo 'Pipeline executed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}

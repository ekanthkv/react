pipeline {
    agent any

    environment {
        DOCKER_USERNAME = 'ekanthkv' // Your Docker Hub username
        IMAGE_NAME = "react_react-app"
        DOCKER_HUB_REPO = "your-dockerhub-username/react-docker-app"
        VERSION = "1.0.0-${env.BUILD_NUMBER}" // Customize version format
        NEXUS_REPO_URL = 'http://localhost:5000/repository/react1/' // Nexus URL
        NEXUS_CREDENTIALS_ID = 'nexus-credentials' // Nexus Credentials ID in Jenkins
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
                      sh  "docker tag react_react-app ${DOCKER_USERNAME}/react_react-app:latest"
                       sh "docker push ${DOCKER_USERNAME}/react_react-app:latest"
                    }
                }
            }
        }

        stage('Push to Nexus') {
            steps {
                script {
                    docker.withRegistry("${NEXUS_REPO_URL}", 'nexus-credentials') {
                        // Tag the image for Nexus
                        sh "docker tag ${DOCKER_USERNAME}/${IMAGE_NAME}:latest localhost:5000/repository/react1/${IMAGE_NAME}:${VERSION}"
                        
                        // Push the versioned image to Nexus
                        sh "docker push localhost:5000/repository/react1/${IMAGE_NAME}:${VERSION}"
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

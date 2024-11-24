pipeline {
    agent any

    environment {
        DOCKER_USERNAME = 'ekanthkv'
        IMAGE_NAME = "react_react-app"
        DOCKER_HUB_REPO = "your-dockerhub-username/react-docker-app"
        SONARQUBE_SERVER = 'sonarqube' // SonarQube server configured in Jenkins
        NEXUS_REPOSITORY_URL = 'http://nexus-server:8081/repository/docker-releases' // Nexus URL
        NEXUS_CREDENTIAL_ID = 'nexus-credential' // Jenkins credential ID for Nexus
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'master', url: 'https://github.com/ekanthkv/react.git'
            }
        }

        stage('Code Analysis with SonarQube') {
            steps {
                withSonarQubeEnv(SONARQUBE_SERVER) {
                    sh """
                    sonar-scanner \
                        -Dsonar.projectKey=react-app \
                        -Dsonar.sources=. \
                        -Dsonar.host.url=http://sonarqube-server:9000 \
                        -Dsonar.login=$SONAR_TOKEN
                    """
                }
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
                        sh "docker tag ${IMAGE_NAME} ${DOCKER_USERNAME}/react-docker-app:latest"
                        sh "docker push ${DOCKER_USERNAME}/react-docker-app:latest"
                    }
                }
            }
        }

        stage('Push to Nexus') {
            steps {
                script {
                    sh """
                    curl -u ${NEXUS_USERNAME}:${NEXUS_PASSWORD} \
                        --upload-file ./target/${IMAGE_NAME}.tar \
                        ${NEXUS_REPOSITORY_URL}/${IMAGE_NAME}:latest
                    """
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

@Library('my-shared-library') _

pipeline {
    agent any

    environment {
        AWS_REGION      = "us-east-1"
        ECR_REPO        = "781978598486.dkr.ecr.us-east-1.amazonaws.com/clouddevops-app"
        IMAGE_NAME      = "${ECR_REPO}"
        IMAGE_TAG       = "${BUILD_NUMBER}"
        MANIFEST_REPO   = "https://github.com/rawdaessamrou/CloudDevOpsProject.git"
        CLUSTER_NAME    = "clouddevops-cluster"
    }

    stages {

        stage('Build Image') {
            steps {
                script {
                    dockerUtils.buildImage("${IMAGE_NAME}:${IMAGE_TAG}")
                }
            }
        }

        stage('Scan Image') {
            steps {
                script {
                    dockerUtils.scanImage("${IMAGE_NAME}:${IMAGE_TAG}")
                }
            }
        }

        stage('Push Image') {
            steps {
                script {
                    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding',
                        credentialsId: 'aws-credentials']]) {
                        sh """
                            aws ecr get-login-password --region ${AWS_REGION} | \
                            docker login --username AWS --password-stdin ${ECR_REPO}
                        """
                        dockerUtils.pushImage("${IMAGE_NAME}:${IMAGE_TAG}")
                    }
                }
            }
        }

        stage('Delete Image Locally') {
            steps {
                script {
                    dockerUtils.deleteLocalImage("${IMAGE_NAME}:${IMAGE_TAG}")
                }
            }
        }

        stage('Update Manifests') {
            steps {
                script {
                    manifestUtils.updateManifest(
                        MANIFEST_REPO,
                        IMAGE_NAME,
                        IMAGE_TAG
                    )
                }
            }
        }

        stage('Push Manifests') {
            steps {
                script {
                    gitUtils.pushManifestChanges()
                }
            }
        }
    }
}
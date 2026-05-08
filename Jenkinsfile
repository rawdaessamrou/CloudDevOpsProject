@Library('shared-library') _

pipeline {
    agent any

    environment {
        IMAGE_NAME = "rawdaessamrou/python-app"
        IMAGE_TAG = "${BUILD_NUMBER}"
        MANIFEST_REPO = "https://github.com/rawdaessamrou/CloudDevOpsProject.git"
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
                    dockerUtils.pushImage("${IMAGE_NAME}:${IMAGE_TAG}")
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
def buildImage(String imageName) {
    sh "docker build -t ${imageName} ."
}

def scanImage(String imageName) {
    sh "trivy image ${imageName}"
}

def pushImage(String imageName) {

    withCredentials([usernamePassword(
        credentialsId: 'docker-credentials',
        usernameVariable: 'DOCKER_USER',
        passwordVariable: 'DOCKER_PASS'
    )]) {

        sh """
            echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin
            docker push ${imageName}
        """
    }
}

def deleteLocalImage(String imageName) {
    sh "docker rmi -f ${imageName}"
}
def buildImage(String imageName) {
    sh "docker build -t ${imageName} ."
}

def scanImage(String imageName) {
    sh "trivy image --exit-code 0 ${imageName}"
}

def pushImage(String imageName) {
    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding',
        credentialsId: 'aws-credentials']]) {
        sh """
            aws ecr get-login-password --region us-east-1 | \
            docker login --username AWS \
            --password-stdin 781978598486.dkr.ecr.us-east-1.amazonaws.com
            docker push ${imageName}
        """
    }
}

def deleteLocalImage(String imageName) {
    sh "docker rmi -f ${imageName}"
}

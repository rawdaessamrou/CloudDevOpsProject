def updateManifest(String repo, String imageName, String imageTag) {
    sh """
        rm -rf CloudDevOpsProject
        git clone ${repo}
        cd CloudDevOpsProject/Kubernates
        sed -i 's|image:.*|image: ${imageName}:${imageTag}|g' deployment.yaml
    """
}

def updateManifest(String repo, String imageName, String imageTag) {
    sh """
        git clone ${repo} CloudDevOpsProject
        cd CloudDevOpsProject
        sed -i 's|image:.*|image: ${imageName}:${imageTag}|g' kubernates/deployment.yaml
    """
}

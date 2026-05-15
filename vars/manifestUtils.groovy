def updateManifest(String repo, String imageName, String imageTag) {
    sh """
        rm -rf CloudDevOpsProject || true
        git clone ${repo} CloudDevOpsProject
        cd CloudDevOpsProject

        sed -i 's|image:.*|image: ${imageName}:${imageTag}|g' Kubernates/deployment.yaml
    """
}

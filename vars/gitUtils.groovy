def pushManifestChanges() {

    withCredentials([usernamePassword(
        credentialsId: 'github-credintials',
        usernameVariable: 'GIT_USER',
        passwordVariable: 'GIT_PASS'
    )]) {

        sh """
            cd k8s-manifests

            git config user.email "rawdaessamrou@example.com"
            git config user.name "rawdaessamrou"

            git add .
            git commit -m "Update image tag"

            git push https://\$GIT_USER:\$GIT_PASS@github.com/rawdaessamrou/CloudDevOpsProject.git
        """
    }
}
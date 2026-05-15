def pushManifestChanges() {
    withCredentials([usernamePassword(
        credentialsId: 'github-credentials',
        usernameVariable: 'GIT_USER',
        passwordVariable: 'GIT_PASS'
    )]) {
        sh """
            cd CloudDevOpsProject
            git config user.email "rawdaessamrou@example.com"
            git config user.name "rawdaessamrou"
            git add Kubernates/deployment.yaml
            git commit -m "ci: update image tag to ${BUILD_NUMBER}" || true
            git push https://\$GIT_USER:\$GIT_PASS@github.com/rawdaessamrou/CloudDevOpsProject.git HEAD:main
        """
    }
}

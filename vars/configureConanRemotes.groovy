def call()
{
    stage("Configure Conan")
    {
        steps
        {
            withCredentials([string(credentialsId: 'ArtifactoryAPIKey', variable: 'CONAN_API_KEY')])
            {
                sh "conan remote clean"
                sh "conan remote add systelab-conan https://artifactory.systelab.net/artifactory/api/conan/systelab-conan --force"
                sh "conan remote add conan-center-remote https://artifactory.systelab.net/artifactory/api/conan/conan-center-remote --force"
                sh "conan remote add systelab-conan-local https://artifactory.systelab.net/artifactory/api/conan/systelab-conan-local --force"

                sh "conan user -p ${CONAN_API_KEY} -r systelab-conan newton-ci"
                sh "conan user -p ${CONAN_API_KEY} -r conan-center-remote newton-ci"
                sh "conan user -p ${CONAN_API_KEY} -r systelab-conan-local newton-ci"

                sh "conan remote list"
                sh "conan user"
            }
        }
    }
}

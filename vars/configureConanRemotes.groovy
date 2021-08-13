def call()
{
	withCredentials([string(credentialsId: 'ArtifactoryAPIKey', variable: 'CONAN_API_KEY')])
	{
		sh label: "Configure conan remotes for C++ task force", script: '''
			conan remote clean
			conan remote add systelab-conan https://artifactory.systelab.net/artifactory/api/conan/systelab-conan --force
			conan remote add conan-center-remote https://artifactory.systelab.net/artifactory/api/conan/conan-center-remote --force
			conan remote add systelab-conan-local https://artifactory.systelab.net/artifactory/api/conan/systelab-conan-local --force

			conan user -p ${CONAN_API_KEY} -r systelab-conan newton-ci
			conan user -p ${CONAN_API_KEY} -r conan-center-remote newton-ci
			conan user -p ${CONAN_API_KEY} -r systelab-conan-local newton-ci

			conan remote list
			conan user
		'''
	}
}

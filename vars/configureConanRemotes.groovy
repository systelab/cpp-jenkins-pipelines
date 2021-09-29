def call()
{
	withCredentials([string(credentialsId: 'ArtifactoryUsername', variable: 'CONAN_USERNAME')])
	{
		withCredentials([string(credentialsId: 'ArtifactoryAPIKey', variable: 'CONAN_API_KEY')])
		{
			sh label: "Configure conan remotes", script: """
				conan remote clean
				conan remote add systelab-conan https://artifactory.systelab.net/artifactory/api/conan/systelab-conan --force
				conan remote add conan-center-remote https://artifactory.systelab.net/artifactory/api/conan/conan-center-remote --force
				conan remote add systelab-conan-local https://artifactory.systelab.net/artifactory/api/conan/systelab-conan-local --force

				conan user -p ${CONAN_API_KEY} -r systelab-conan ${CONAN_USERNAME}
				conan user -p ${CONAN_API_KEY} -r conan-center-remote ${CONAN_USERNAME}
				conan user -p ${CONAN_API_KEY} -r systelab-conan-local ${CONAN_USERNAME}

				conan remote list
				conan user
			"""
	}
	}
}

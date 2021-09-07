def call(version)
{
	checkout(
		changelog: true,
		poll: true,
		scm: [
			$class: 'GitSCM',
			branches: [[name: 'refs/tags/v${version}']],
			extensions: scm.extensions + 
				[[
					$class: 'CloneOption',
					shallow: true,
					noTags: false,
					reference: '',
					timeout: 10
				]],
			userRemoteConfigs: scm.userRemoteConfigs
		]
	)
}

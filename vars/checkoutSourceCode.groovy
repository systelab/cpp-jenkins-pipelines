def call()
{
	checkout(
		changelog: true,
		poll: true,
		scm: [
			$class: 'GitSCM',
			branches: scm.branches,
			extensions: scm.extensions + 
				[[
					$class: 'CloneOption',
					shallow: true,
					noTags: true,
					reference: '',
					timeout: 10
				]],
			userRemoteConfigs: scm.userRemoteConfigs
		]
	)
}

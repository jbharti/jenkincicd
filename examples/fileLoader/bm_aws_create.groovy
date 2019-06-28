def version = '1.0'

def createEC2(String machine_name, String workspace_path, String aws_machine_path, String notification) {  
	build job: '/bm/aws/create/createEC2', parameters: [[$class: 'NodeParameterValue', name: 'MACHINE', labels: [machine_name], nodeEligibility: [$class: 'AllNodeEligibility']],string(name: 'WORKSPACE', value: workspace_path), string(name: 'AWS_MACHINE', value: ''), string(name: 'VAGRANT_PATH', value: aws_machine_path), string(name: 'EMAIL_TO', value: notification)]
}

return this;
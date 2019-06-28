def version = '1.0'

def createEC2(String machine_name, String workspace_path, String aws_machine_path) {  
	build job: '/bm/aws/status/getVagrantMachineStatus', parameters: [[$class: 'NodeParameterValue', name: 'MACHINE', labels: [machine_name], nodeEligibility: [$class: 'AllNodeEligibility']],string(name: 'WORKSPACE', value: workspace_path), string(name: 'VAGRANT_PATH', value: aws_machine_path), string(name: 'STATUS', value: "not created")]
}

return this;
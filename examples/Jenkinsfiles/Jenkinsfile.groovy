repository = 'https://github.com/jbharti/Jenkinsfile.git'
branch = 'master'
credentialsId = null
labelExpression = ''

properties([parameters([string(defaultValue: '5.0.0', description: '', name: 'RELEASE_NUMBER', trim: false),string(defaultValue: '18', description: '', name: 'IR_NUMBER', trim: false),booleanParam(defaultValue: true, description: 'Check, If you want to create SAHI_SLAVE1', name: 'SAHI_SLAVE1')])])

machine_name = "VAGRANT-MACHINE"
Server_s3Target = "digitedevops/swiftalm_releases/"+"${RELEASE_NUMBER}"+"/"+"${IR_NUMBER}"+"/jboss/"
Server_File_Name = "jboss-digite.zip"
notification = "jpbharti@digite.com"
workspace_path = "/home/administrator/app/work/infra_automation/swiftalm-automation"
slave1_machine = "SAHI-SLAVE-20"
slave1_machine_path = "sahi/slave-setup20"
rtc_sahi_slave1="RTC-"+slave1_machine

def bm_aws_deploy,bm_aws_create,bm_aws_status
fileLoader.withGit(repository, branch) {
	bm_aws_deploy = fileLoader.load('examples/fileLoader/bm_aws_deploy');
	bm_aws_create = fileLoader.load('examples/fileLoader/bm_aws_create'); 
	bm_aws_status = fileLoader.load('examples/fileLoader/bm_aws_status'); 	
}
stage('Check If Build Exist On S3') {
	echo "Check for Build on S3"	
	bm_aws_deploy.checkIfBuildExistOnS3(machine_name,Server_s3Target,Server_File_Name,notification)
}
parallel SLAVE1: {
			stage(rtc_sahi_slave1) {				
					script {
						      if("${SAHI_SLAVE1}" == "true"){
						       try{
									     echo 'Creating '+ rtc_sahi_slave1
									     bm_aws_create.checkIfBuildExistOnS3(machine_name,workspace_path,slave1_machine_path,notification)			
								   }catch(err){
									     echo "First build failed, let's retry once more"
										 retry(1) {
                                             sleep 90
                                             bm_aws_status.checkIfBuildExistOnS3(machine_name,workspace_path,slave1_machine_path)
											 bm_aws_create.checkIfBuildExistOnS3(machine_name,workspace_path,slave1_machine_path,notification)
										 }
											  
								   } 
							  }																
					    } 
		       }    
			
    }
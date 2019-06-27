repository = 'https://github.com/jbharti/Jenkinsfile.git'
branch = 'master'
credentialsId = null
labelExpression = ''

properties([parameters([string(defaultValue: '5.0.0', description: '', name: 'RELEASE_NUMBER', trim: false),string(defaultValue: '18', description: '', name: 'IR_NUMBER', trim: false)])])

machine_name = "VAGRANT-MACHINE"
Server_s3Target = "digitedevops/swiftalm_releases/"+"${RELEASE_NUMBER}"+"/"+"${IR_NUMBER}"+"/jboss/"
Server_File_Name = "jboss-digite.zip"
notification = "jpbharti@digite.com"

def bm_aws_deploy
fileLoader.withGit(repository, branch) {
	bm_aws_deploy = fileLoader.load('examples/fileLoader/bm_aws_deploy');    
}
stage('Check If Build Exist On S3') {	
	bm_aws_deploy.checkIfBuildExistOnS3(machine_name,Server_s3Target,Server_File_Name,notification)
}
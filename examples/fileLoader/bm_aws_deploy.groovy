/**
 * Prints a stub message for testing purposes.
 */

def version = '1.0'

def checkIfBuildExistOnS3(String machine_name, String Server_s3Target,String Server_File_Name,String notification) {  
	build job: 'bm/aws/deploy/checkIfBuildExistOnS3', parameters: [[$class: 'NodeParameterValue', name: 'MACHINE', labels: [machine_name], nodeEligibility: [$class: 'AllNodeEligibility']], string(name: 'S3_Target_Url', value: Server_s3Target), string(name: 'File_Name', value: Server_File_Name), string(name: 'EMAIL_TO', value: notification)]
}

return this;
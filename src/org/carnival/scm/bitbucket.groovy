#!groovy

/******************************************************************
***** Description :: This Package is used to Checkout the Code ****
***** Author      :: GTA CM Team                        			 ****
***** Date        :: 024/01/2017                               ****
*******************************************************************/

package org.carnival.scm

String WORKSPACE = pwd()

/**************************************************
***** Function to checkout the Application Codebase
***************************************************/
def gitCheckout()
{
   try {

	   BRANCH = scm.getBranches()[0]
       REPOSITORY = scm.getUserRemoteConfigs()[0].getUrl()

       if ( "${env.BRANCH_NAME}" != "null" ) {
	      BRANCH = "${env.BRANCH_NAME}"
	   }
	   if ( "$REPOSITORY" == "null" )
       {
          error "\u001B[41m[ERROR] unable to get Git repository name....."
       }
       if ( "$BRANCH" == "null" )
       {
          error "\u001B[41m[ERROR] unable to get Git Branch name...."
       }
       wrap([$class: 'AnsiColorBuildWrapper']) {
          CREDENTIAL_ID = 'git-credentials'
          println "\u001B[32m[INFO] checking out from branch ${BRANCH}, please wait..."
          // checkout([$class: 'GitSCM', branches: [[name: "${BRANCH}"]], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CloneOption', noTags: false, reference: '', shallow: true]], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "${CREDENTIAL_ID}", url: "${REPOSITORY}"]]])
		      checkout scm

		      env.GIT_BRANCH = "${BRANCH}"
          env.GIT_URL = "$REPOSITORY"
         }
	   currentBuild.result = 'SUCCESS'
   }
   catch (error) {
       wrap([$class: 'AnsiColorBuildWrapper']) {
          print "\u001B[41m[ERROR] clone for repository ${env.GIT_URL} failed, please check the logs..."
          throw error
       }
	   currentBuild.result = 'FAILED'
   }

}

/*********************************************************
***** Function to checkout the Gatling Simulation Codebase
**********************************************************/
def gitGatlingSimulation(BRANCH , REPOSITORY)
{
   try {

	   if ( "$REPOSITORY" == "null" )
       {
          error "\u001B[41m[ERROR] unable to get Git repository name....."
       }
       if ( "$BRANCH" == "null" )
       {
          error "\u001B[41m[ERROR] unable to get Git Branch name...."
       }
       echo "${BRANCH}"
       wrap([$class: 'AnsiColorBuildWrapper']) {
          CREDENTIAL_ID = '66a19d3e-71e8-4d26-bd64-b232469f65e2'
          println "\u001B[32m[INFO] checking out from branch ${BRANCH}, please wait..."
          checkout([$class: 'GitSCM', branches: [[name: "${BRANCH}"]], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CloneOption', noTags: false, reference: '', shallow: true]], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "${CREDENTIAL_ID}", url: "${REPOSITORY}"]]])
		}
	   currentBuild.result = 'SUCCESS'
   }
   catch (error) {
       wrap([$class: 'AnsiColorBuildWrapper']) {
          print "\u001B[41m[ERROR] clone for repository ${REPOSITORY} failed, please check the logs..."
          throw error
       }
	   currentBuild.result = 'FAILED'
   }
   
}

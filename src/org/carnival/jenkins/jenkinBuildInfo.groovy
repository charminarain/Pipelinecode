#!groovy

/**************************************************************
***** Description :: This Package is used to get the Jenkins Build info   *****
***** Author      :: GTA CM Team                        								        *****
***** Date        :: 024/01/2017                                                                 *****
***************************************************************/
package com.mesh.devops.jenkins

def getBuildOwnerInfo()
{
   try {
      def specificCause = currentBuild.rawBuild.getCause(hudson.model.Cause$UserIdCause)
      print "${specificCause}"
   }
   catch (error) {
      print "ERROR: failed to get Jenkins build owner name..."
      throw(error)
   }
}

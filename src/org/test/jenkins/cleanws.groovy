#!groovy

/**************************************************************
***** Description :: This Package is used to Clean Jenkins Workspace   *****
***** Author      :: GTA CM Team                        								        *****
***** Date        :: 024/01/2017                                                                 *****
***************************************************************/
package org.test.jenkins

def cleanWS()
{
   try {
      print "\u001B[32m[INFO]:  cleaning workspace ${WORKSPACE}"
      step([$class: 'WsCleanup'])
   }
   catch (Exception caughtError) {
     wrap([$class: 'AnsiColorBuildWrapper']) {
        print "\u001B[41m[ERROR]: failed to cleanup workspace ${WORKSPACE}, check the logs..."
        currentBuild.result = "FAILURE"
        throw caughtError
     }
   }
}

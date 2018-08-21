#!groovy

/**************************************************************
***** Description :: This Package is used for  Gradle							    *****
***** Author      :: GTA CM Team                        								        *****
***** Date        :: 024/01/2017                                                                 *****
***************************************************************/

package com.mesh.devops.tools

def setMavenHome(VERSION)
{
   try {
     wrap([$class: 'AnsiColorBuildWrapper']) {
       env.MAVEN_VERSION="${tool "${VERSION}"}"
       echo "'${MAVEN_VERSION}'"
      //echo "'${MAVEN_HOME}'"
       sh "${MAVEN_VERSION}/bin/mvn --version"
	   }
   }
   catch (error) {
     wrap([$class: 'AnsiColorBuildWrapper']) {
        print "\u001B[41m[ERROR]: failed to failed to set Maven ."
        throw error
     }
   }
}
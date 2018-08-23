#!groovy

/**************************************************************
***** Description :: This Package is used for  Gradle							    *****
***** Author      :: GTA CM Team                        								        *****
***** Date        :: 024/01/2017                                                                 *****
***************************************************************/

package org.carnival.tools

def setMavenHome(VERSION)
{
   try {
     wrap([$class: 'AnsiColorBuildWrapper']) {
       
       echo "1"
	   env.MAVEN_HOME="${tool "${VERSION}"}"
	   echo "2"
       env.PATH="${env.MAVEN_HOME}\bin:${env.PATH}"
       echo "3"
       bat "mvn --version"
	   }
   }
   catch (error) {
     wrap([$class: 'AnsiColorBuildWrapper']) {
        print "\u001B[41m[ERROR]: failed to failed to set Maven ."
        throw error
     }
   }
}

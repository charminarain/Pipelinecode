#!groovy

/**************************************************************
***** Description :: This Package is used for  JDK							        *****
***** Author      :: GTA CM Team                        								        *****
***** Date        :: 024/01/2017                                                                 *****
***************************************************************/
package org.carnival.tools

def setJavaHome(VERSION)
{
   try {
     wrap([$class: 'AnsiColorBuildWrapper']) {
	   echo "1"
       env.JAVA_HOME="${tool "${VERSION}"}"
	   echo "2"
       env.PATH="${env.JAVA_HOME}\bin:${env.PATH}"
	   echo "3"
       bat 'java -version'
		
     }
   }
   catch (error) {
     wrap([$class: 'AnsiColorBuildWrapper']) {
         print "\u001B[41m[ERROR]: failed to set JAVA_HOME to ${JAVA_HOME}..."
        throw error
     }
   }
}

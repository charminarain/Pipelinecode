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
       env.JAVA_HOME="${tool "${VERSION}"}"
       env.PATH="${env.JAVA_HOME}\bin:${env.PATH}"
       sh 'java -version'
		
     }
   }
   catch (error) {
     wrap([$class: 'AnsiColorBuildWrapper']) {
         print "\u001B[41m[ERROR]: failed to set JAVA_HOME to ${JAVA_HOME}..."
        throw error
     }
   }
}

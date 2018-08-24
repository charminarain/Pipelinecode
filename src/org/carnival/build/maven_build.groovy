#!groovy

package org.carnival.build

def mavenbuild(PATH_POM ,TASKS ){
     try {
    wrap([$class: 'AnsiColorBuildWrapper']) {
    //echo "${WORKSPACE}"
    //echo "${MAVEN_VERSION}"
	bat "mvn -file '${PATH_POM}' ${TASKS}"
    print "\u001B[32m[INFO]: Successfully Executing the Build..."
	}
    }
    catch (error) {
     wrap([$class: 'AnsiColorBuildWrapper']) {
        print "\u001B[41m[ERROR]: failed to Build"
        throw error
     }
   }
}

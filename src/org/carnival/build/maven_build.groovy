#!groovy

package org.carnival.build

def mavenbuild(POM_PATH , MAVEN_VERSION ,TASKS ){
     try {
    wrap([$class: 'AnsiColorBuildWrapper']) {
    echo "${WORKSPACE}"
    echo "${MAVEN_VERSION}"
	sh "'${MAVEN_VERSION}'/bin/mvn -f '${PATH_POM}' ${TASKS}"
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
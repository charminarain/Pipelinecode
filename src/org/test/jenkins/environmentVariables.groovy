/**********************************************************************************
***** Groovy Script :: environmentVariables.groovy                            *****
***** Description   :: This groovy code is used to perform the sonar analysis *****
***********************************************************************************/
package org.test.jenkins

def setEnvVariablesForPipeline()
{
   try {
     println "\u001B[32mINFO => Injecting Sonar environment Variables please wait..."
     def jobConfiguration = libraryResource 'com/mesh/devops/mesh-configurations.properties'
     writeFile file: 'mesh-configurations.properties', text: jobConfiguration
     def props = readProperties file: "mesh-configurations.properties"
     env.REGISTRY_NAME = props['dockerregurl']
     env.NEXUS_HOST_URL = props['nexusUrl']
     env.NEXUS_CREDENTIAL_ID = props['credentialsId']
     env.NEXUS_PROTOCAL = props['protocol']
     env.NEXUS_VERSION =  props['nexusVersion']
   }
   catch (Exception caughtError)
   {
      currentBuild.result = "FAILURE"
      println "\u001B[41m[ERROR] Failed to set Sonar environment variables"
      throw caughtError
   }
}

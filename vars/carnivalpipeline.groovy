#!groovy

/* IMPORTING MODULE */
import org.carnival.scm.*
import org.carnival.build.*
import org.carnival.tools.*
import org.carnival.jenkins.*

def call(body)
{
   def config = [:]
   body.resolveStrategy = Closure.DELEGATE_FIRST
   body.delegate = config
   body()
   def scm = new bitbucket()
   def maven = new maven_build()
   
   
   timestamps {
 
    stage('Preparing CI Environment'){
        try {
          currentBuild.result = "SUCCESS"
          def wspace = new cleanws()
          wspace.cleanWS()
         // jobprop.setProperties()
      }
      catch (Exception error)
        {
          wrap([$class: 'AnsiColorBuildWrapper']) {
            echo "\u001B[41m[ERROR] ${error}"
            throw error
          }
      }
    }
    stage('Initializing Devops tools'){
      try {
          def j = new jdk()
          wrap([$class: 'AnsiColorBuildWrapper']) {
            def VERSION = "Java-10.0.2"
            j.setJavaHome("${VERSION}")

          }
        }
        catch (error)
        {
          wrap([$class: 'AnsiColorBuildWrapper']) {
              echo "JAVA Initializing Failed..."
              throw error
          }
        }
        try {
        def m = new maven()
        wrap([$class: 'AnsiColorBuildWrapper']) {
		    def VERSION = "Maven-3.5.3"
            m.setMavenHome("${VERSION}")
          }
        }
        catch (error){
          wrap([$class: 'AnsiColorBuildWrapper']) {
              throw error
          }
        }
    }
    stage('Checking Codeout '){
      try {
      scm.gitCheckout()
      echo "\u001B[41m[SUCCESS] Source Code successfully downloaded"
      }
      catch (Exception error)
      {
          wrap([$class: 'AnsiColorBuildWrapper']) {
          echo "\u001B[41m[ERROR] ${error}"
          throw error
          }
      }
    }
    stage('Building'){
        try{
          //echo "${MAVEN_VERSION}"
          def TASKS = "clean install"
          maven.mavenbuild("${WORKSPACE}\addressbook_main" , "${TASKS}")
          }
          catch (Exception error){
          wrap([$class: 'AnsiColorBuildWrapper']) {
            print "\u001B[41m[INFO]: ${error}"
            throw error
          }
      }
    }
    /*stage('Publish Artifact'){
      def GROUP_ID = "com.cognitivesearch"
      def VERSION = "0.0.1"
      def REPOSITORY_NAME = "releases"
      def PROJECT_NAME = "cognitive-pipeline"
      def nexus = new publishNexusRepo()
      nexus.publishMavenArtifactsToNexus("${NEXUS_VERSION}","${NEXUS_PROTOCAL}","${NEXUS_HOST_URL}","${GROUP_ID}","${VERSION}","${REPOSITORY_NAME}","${NEXUS_CREDENTIAL_ID}","${PROJECT_NAME}")
    }*/
}
}

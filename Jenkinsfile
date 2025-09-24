pipeline {
  agent any
  tools {
    jdk 'jdk-21'
    maven 'maven-3.9'
  }
  options { timestamps() }
  stages {
    stage('Checkout') { steps { checkout scm } }
    stage('Unit Tests') {
      steps {
        bat 'mvn -B test'
        // bat 'mvn -B -Dmaven.test.failure.ignore=true test'
      }
    }
  }
  post {
    always {
      junit 'target/surefire-reports/*.xml'
      archiveArtifacts artifacts: 'target/surefire-reports/*.xml', fingerprint: true
    }
  }
}

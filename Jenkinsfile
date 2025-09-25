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
  post {
    always {
      // 1) Publica los XML para la vista nativa de Jenkins
      junit 'target/surefire-reports/*.xml'

      // 2) Publica el HTML “bonito”
      publishHTML(target: [
        reportDir: 'target/site',
        reportFiles: 'surefire-report.html',
        reportName: 'Unit Test Report',
        keepAll: true,            // conserva el histórico por build
        alwaysLinkToLastBuild: true,
        allowMissing: false
      ])

      // (Opcional) Guarda el HTML como artefacto del build
      archiveArtifacts artifacts: 'target/site/surefire-report.html', fingerprint: true
    }
  }
}




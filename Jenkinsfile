pipeline {
  agent any
  tools { jdk 'jdk-21'; maven 'maven-3.9' }
  options { timestamps() }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Unit Tests + HTML Report') {
      steps {
        // A) Si quieres que el build NO falle por tests rojos (queda UNSTABLE):
        bat 'mvn -B -Dmaven.test.failure.ignore=true test surefire-report:report'

        // B) Si quieres que falle cuando hay fallos (SUCCESS/FAILURE), usa esto en lugar de A:
        // bat 'mvn -B test surefire-report:report'
      }
    }
  }

  post {
    always {
      // Vista nativa de tests
      junit 'target/surefire-reports/*.xml'

      // Reporte HTML “bonito” (instala el plugin HTML Publisher)
      publishHTML(target: [
        reportDir: 'target/site',
        reportFiles: 'surefire-report.html',
        reportName: 'Unit Test Report',
        keepAll: true,
        alwaysLinkToLastBuild: true,
        allowMissing: false
      ])

      // (Opcional) Guarda el HTML como artefacto
      archiveArtifacts artifacts: 'target/site/surefire-report.html', fingerprint: true
    }
  }
}




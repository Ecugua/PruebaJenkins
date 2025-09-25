pipeline {
  agent any
  tools { jdk 'jdk-21'; maven 'maven-3.9' }
  options { timestamps() }

  stages {
    stage('Checkout') { steps { checkout scm } }

    stage('Unit Tests + HTML Report') {
      steps {
        // Build rápido que NO rompe si hay fallos (queda UNSTABLE)
        bat 'mvn -B -Dmaven.test.failure.ignore=true test surefire-report:report'
        // Si prefieres que falle cuando haya fallos, usa esta línea en lugar de la de arriba:
        // bat 'mvn -B test surefire-report:report'
      }
    }
  }

  post {
    always {
      // Vista nativa de Jenkins (barra verde/roja)
      junit 'target/surefire-reports/*.xml'

      // Publica el HTML sencillo
      publishHTML(target: [
        reportDir: 'target/site',
        reportFiles: 'surefire-report.html',
        reportName: 'Unit Test Report',
        keepAll: true,
        alwaysLinkToLastBuild: true,
        allowMissing: false
      ])

      // (Opcional) guardar el HTML como artefacto
      archiveArtifacts artifacts: 'target/site/surefire-report.html', fingerprint: true
    }
  }
}

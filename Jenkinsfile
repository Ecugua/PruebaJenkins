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
        // Build rápido que NO rompe si hay fallos (queda UNSTABLE)
        bat 'mvn -B -Dmaven.test.failure.ignore=true test surefire-report:report'
        // Si prefieres que falle cuando haya fallos, usa esta línea en lugar de la de arriba:
        // bat 'mvn -B test surefire-report:report'

        // === EXTRA: Convertir HTML -> PDF con Chrome headless ===
        // Ajusta la ruta si Chrome está instalado en otra ubicación.
        bat """
        if exist target\\site\\surefire-report.html (
          \"C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe\" ^
            --headless=new --disable-gpu ^
            --print-to-pdf=\"%CD%\\target\\surefire-report.pdf\" ^
            \"%CD%\\target\\site\\surefire-report.html\"
        ) else (
          echo No se encontro target\\site\\surefire-report.html
        )
        """
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

      // Guarda el HTML y el PDF como artefactos
      archiveArtifacts artifacts: 'target/site/surefire-report.html', fingerprint: true
      archiveArtifacts artifacts: 'target/surefire-report.pdf', fingerprint: true
    }
  }
}

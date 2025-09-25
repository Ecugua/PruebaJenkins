pipeline {
  agent any
  tools { jdk 'jdk-21'; maven 'maven-3.9' }
  options { timestamps() }

  stages {
    stage('Checkout') { steps { checkout scm } }

    stage('Tests + Site') {
      steps {
        // No rompas el build por los 2 tests rojos (UNSTABLE):
        bat 'mvn -B -Dmaven.test.failure.ignore=true test site'
        // Si quieres que falle cuando hay rojos, usa esto en lugar de la línea de arriba:
        // bat 'mvn -B test site'
      }
    }
  }

  post {
    always {
      // Vista nativa de pruebas (barra verde/roja)
      junit 'target/surefire-reports/*.xml'

      // Publica el sitio con skin Fluido (index con menú/estilos)
      publishHTML(target: [
        reportDir: 'target/site',
        reportFiles: 'index.html',
        reportName: 'Project Site',
        keepAll: true,
        alwaysLinkToLastBuild: true
      ])

      // Link directo al informe de tests
      publishHTML(target: [
        reportDir: 'target/site',
        reportFiles: 'surefire-report.html',
        reportName: 'Unit Test Report',
        keepAll: true,
        alwaysLinkToLastBuild: true
      ])
    }
  }
}


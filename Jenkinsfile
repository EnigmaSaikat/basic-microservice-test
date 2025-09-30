pipeline {
  agent any

  options {
    timestamps()
    ansiColor('xterm')
  }

  tools {
    // Configure these tool names in Jenkins global config
    jdk 'temurin-17'
    maven 'maven-3'
  }

  parameters {
    booleanParam(name: 'BUILD_DOCKER', defaultValue: false, description: 'Build Docker images with Compose')
  }

  environment {
    MAVEN_OPTS = '-DskipTests=false'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Test') {
      steps {
        sh 'mvn -q -DskipTests=false test'
      }
      post {
        always {
          junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
        }
      }
    }

    stage('Build Docker Images') {
      when { expression { return params.BUILD_DOCKER as boolean } }
      steps {
        sh 'docker --version || true'
        // Try Compose V2 first; if unavailable, fall back to legacy docker-compose
        sh '(docker compose version && docker compose build) || (docker-compose --version && docker-compose build)'
      }
    }
  }

  post {
    success { echo 'Build succeeded' }
    failure { echo 'Build failed' }
  }
}

pipeline {
    agent any

    tools {
        jdk 'Java_JDK'
        maven 'MAVEN'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/nextgentestingacademy/JenkinsIntegratedCode.git',
                    branch: 'main',
                    credentialsId: 'git-github-token'
            }
        }

        stage('Build & Test') {
            steps {
                bat "mvn clean test"
            }
        }

        stage('Publish TestNG Results') {
            steps {
                junit '**/testng-results.xml'
            }
        }

        stage('Archive TestNG HTML Report') {
            steps {
                archiveArtifacts artifacts: 'test-output/**/*.html', fingerprint: true
            }
        }

    }
}

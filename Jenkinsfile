pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/nextgentestingacademy/POMDesign.git',
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
                junit 'test-output/testng-results.xml'
            }
        }
    }
}
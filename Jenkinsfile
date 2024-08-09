pipeline {
    agent any
    stages {
          stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build'){
            steps{
                bat 'mvn clean install'
            }
        }
        stage('Test'){
            steps{
                bat 'mvn test'
            }
        }
         stage('Clean Up') {
            steps {
                bat 'rmdir /s /q target'
            }
        }
    }
}

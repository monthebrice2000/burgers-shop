pipeline{
    agent { label 'master' }
    environment {
        imageName = 'emarketshop'
        PROJECT_VERSION = '0.0.1'
    }
    stages{
        stage('Checkout'){
            steps{
                checkout scm
                echo 'Successfully checkout'
                sh "${tool 'M2_HOME'}/bin/mvn --help"
                sh "${tool 'JAVA_HOME'}/bin/java --help"
            }
        }
        stage('Pre-integrated Tests'){
            parrallel {
                stage('Code Style Test') {
                    steps{
                        echo 'CI/CD: Code Style Tests'
                    }
                }
                stage('Unit Test') {
                    steps{
                        echo 'CI/CD: Unit Tests'
                    }
                }
                stage('Security Test') {
                    steps{
                        echo 'CI/CD: Security Tests'
                    }
                }
                stage('Code Coverage Test') {
                    steps{
                        echo 'CI/CD: Code Coverage Tests'
                    }
                }
            }
            
        }
        stage('Sonarqube Analysis Test') {
            stages {
                stage ('Sonarqueb Code Analysis'){
                    steps{
                        echo 'CI/CD: Sonarqube Code Quality Tests'
                    }
                }
                stage('Sonarqube Quality Gate'){
                    steps{
                        echo 'CI/CD: Sonarqube Code Quality Gates Tests'
                    }
                }
            }
        }
        stage('Build Image'){
            steps{
                echo 'CI/CD : Build Image Step'
            }
        }
        stage('Push Image'){
            steps{
                echo 'CI/CD : Push Image Step'
            }
        }
        stage('Deploy Release'){
            steps{
                echo 'CI/CD : Deploy Step'
            }
        }
    }
}
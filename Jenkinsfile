pipeline{
    agent { label 'master' }
    environment {
        imageName = 'emarketshop'
        PROJECT_VERSION = '0.0.1'
        M2_HOME = tool 'M2_HOME'
        JAVA_HOME = tool 'JAVA_HOME'
    }
    stages{
        stage('Checkout'){
            steps{
                checkout scm
                echo 'Successfully checkout'
            }
        }
        stage('Pre-integrated Tests'){
            parallel {
                stage('Code Style Test') {
                    steps{
                        echo 'CI/CD: Code Style Tests'
                    }
                }
                stage('Unit Test') {
                    steps{
                        echo 'CI/CD: Unit Tests'
                        cd './burgers-admin-app'
                        sh "${M2_HOME}/bin/mvn clean test"
                        echo 'Successfully Unit Test of burgers-admin-app'

                        cd './burgers-shop-app'
                        sh "${M2_HOME}/bin/mvn clean test"

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
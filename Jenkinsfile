pipeline{
    agent { label 'master' }
    environment {
        IMAGE_NAME = "eburgers-shop"
        PROJECT_VERSION = '0.0.1'
        M2_HOME = tool 'M2_HOME'
        JAVA_HOME = tool 'JAVA_HOME'
        SONARQUBE_URL = "http://20.224.44.44:9000"
        SONARQUBE_PROJECT_NAME = "burgers-shop-sq"
        // SONARQUBE_CREDENTIALS_ID = "sonarqube-creds"
        // SONARQUBE_PROJECT_KEY = "eburgers-shop"
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
                        sh "cd './burgers-admin-app' && ${M2_HOME}/bin/mvn clean test"
                        echo 'Successfully Unit Test of burgers-admin-app'

                        sh "cd './burgers-shop-app' && ${M2_HOME}/bin/mvn clean test"
                        echo 'Successfully Unit Test of burgers-shop-app'

                    }
                    post {
                        always {
                            junit '**/target/surefire-reports/*.xml'
                            echo 'Successfully Reports Unit results tests for burgers-shop'
                        }
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
        stage ('Sonarqueb Code Analysis'){
            steps{
                echo 'Running SonarQube analysis...'
                withCredentials([
                    string(credentialsId: 'sonarque-creds-1', variable: 'SONARQUBE_CREDENTIALS_TOKEN'),
                    string(credentialsId: 'sonarque-creds-2', variable: 'SONARQUBE_PROJECT_KEY')
                    // usernamePassword(credentialsId: 'sonarque-creds-1', usernameVariable: 'SONARQUBE_CREDENTIALS-1', passwordVariable: 'SONARQUBE_CREDENTIALS_TOKEN'),
                    // usernamePassword(credentialsId: 'sonarque-creds-2', usernameVariable: 'SONARQUBE_CREDENTIALS-2', passwordVariable: 'SONARQUBE_PROJECT_KEY')
                ]){
                    withSonarQubeEnv('SonarQube Server') {
                        sh "cd './burgers-admin-app' && ${M2_HOME}/bin/mvn clean verify -DskipTests sonar:sonar -Dsonar.projectName=${SONARQUBE_PROJECT_NAME} -Dsonar.projectKey=${SONARQUBE_PROJECT_KEY} -Dsonar.host.url=${SONARQUBE_URL} -Dsonar.token=${SONARQUBE_CREDENTIALS_TOKEN}"
                    }
                }
                echo 'Sonarqube Analysis Successfully...'
                
            }
        }
        stage('Sonarqube Quality Gate'){
            steps{
                echo 'CI/CD: Sonarqube Code Quality Gates Tests'
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

    post {
        always {
            echo 'Cleaning up...'
            cleanWs()
        }
        success {
            
            echo 'Pipeline completed successfully!'
            
            // archiveArtifacts artifacts: "**/target/*.jar"
            // echo 'Archiving artifacts successfully !!!'
            
            emailext (
                to: 'test@example.com',
                subject: "Build Success - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """<p>Job '${env.JOB_NAME}' (#${env.BUILD_NUMBER}) was successful.</p>
                         <p>Check console output at <a href="${env.BUILD_URL}">${env.BUILD_URL}</a>.</p>"""
            )
            echo 'Email Send Successfully...'
        }
        failure {
            echo 'Pipeline failed. Notifying team...'
            emailext (
                to: 'test@example.com',
                subject: "Build Failure - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """<p>Job '${env.JOB_NAME}' (#${env.BUILD_NUMBER}) has failed.</p>
                         <p>Check console output at <a href="${env.BUILD_URL}">${env.BUILD_URL}</a>.</p>"""
            )
            echo 'Email Send successfully...'
            // Add notification logic here, e.g., email or Slack notification

        }
    }
}
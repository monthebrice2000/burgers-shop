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
    }
}
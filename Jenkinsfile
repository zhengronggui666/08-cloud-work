pipeline {
    agent none
    stages{
        stage('Clone to master'){
            agent{
                label 'master'
            }
            steps{
                echo "1.Git Clone Stage"
                git url:""
            }
        }
    }
}

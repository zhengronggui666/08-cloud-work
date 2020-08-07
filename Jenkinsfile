pipeline {
    agent none
    stages{
        stage('Clone to master'){
            agent{
                label 'master'
            }
            steps{
                echo "1.Git Clone Stage"
                git url: "https://github.com/0820sdd/prometheus-test-demo.git"
            }
        }
        stage('Maven Build'){
            agent{
                docker{
                    image 'maven:latest'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps{
                echo "2.Maven Build Stage"
                sh 'mvn -B clean package -Dmaven.test.skip=true'
            }
        }
        stage('Image Build'){
            agent{
                label 'master'
            }
            steps{
                echo "3.Image Build Stage"
                sh 'docker build -f Dockerfile --build-arg jar_name=target/prometheus-test-demo-0.0.1-SNAPSHOT.jar -t prometheus-test-demo:${BUILD_ID} . '
                sh 'docker tag prometheus-test-demo:${BUILD_ID} harbor.edu.cn/library/prometheus-test-demo:${BUILD_ID}'
            }
        }
        stage('Push'){
            agent{
                label 'master'
            }
            steps{
                echo "4.Push Docker Image Stage"
                sh "docker login --username=admin harbor.edu.cn -p Harbor12345"
                sh "docker push harbor.edu.cn/library/prometheus-test-demo:${BUILD_ID}"
            }
        }
    }
}

node('slave'){
    container('jnlp-kubectl'){
        
        stage('Git Cone'){
        git url: "https://github.com/0820sdd/prometheus-test-demo.git"
        }
        
        stage('YAML'){
        echo "5.Chage YAML File Stage"
        sh 'sed -i "s#{VERSION}#${BUILD_ID}#g" ./jenkins/scripts/prometheus-test-demo.yaml'
        }
        
        stage('Deploy'){
        echo "5.Deploy To K8s Stage"
        sh 'kubectl apply -f ./jenkins/scripts/prometheus-test-demo.yaml -n default'
        }
    }
}

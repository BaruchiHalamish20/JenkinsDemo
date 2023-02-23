pipeline {
  agent any
  environment {
    dockerhub = credentials('dockerhubc')
    dockerImage = ''
  }
  
  stages {
    
    stage('Login') {
      steps {
          sh 'whoami'
          sh 'hostname'
        sh 'echo $dockerhub_PSW | docker login -u $dockerhub_USR --password-stdin'
      }
  }
    
    stage('create internal network') {
      steps {
          sh 'docker network inspect elbit_network >/dev/null 2>&1 || docker network create --driver=bridge --subnet=172.28.0.0/16 --ip-range=172.28.5.0/24 --gateway=172.28.5.254 elbit_network'
      }
  }
    
    
  }
}
pipeline{

    agent none 

    stages{
      stage("server"){
        when{
          changeset "**/syslog-server/**"
        }
        stages{
          stage("build-server"){
              agent{
                  docker{
                    image 'maven:3.6.1-jdk-8-slim'
                    args '-v m2cache:/root/.m2'
                  }
              } 

              steps{
                  echo "Building syslog server app..."
                  dir("syslog-server"){
                    sh 'mvn compile'
                  }
              }
          }
          stage("test-server"){
              agent{
                  docker{
                    image 'maven:3.6.1-jdk-8-slim'
                    args '-v m2cache:/root/.m2'
                  }
              }
              steps{
                  echo "Testing syslog server app..."
                  dir("syslog-server"){
                    sh 'mvn clean test'
                  }
              }
          }
          stage("package-server"){
              agent{
                  docker{
                    image 'maven:3.6.1-jdk-8-slim'
                    args '-v m2cache:/root/.m2'
                  }
              }
              steps{
                  echo "Packaging syslog server app..."
                  dir("syslog-server"){
                    sh 'mvn package -DskipTests'
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                  }
              }
          }
          stage('server-copy-artifact'){
            agent any
            steps{
                echo "copying artifact from 'package-server' stage"
                  copyArtifacts filter: '**/target/*.jar', fingerprintArtifacts: true, projectName: '${JOB_NAME}', selector: specific('${BUILD_NUMBER}'), target: './syslog-server/'
                  sh 'ls -la ./syslog-server/target'
            }

          }
          stage('docker-package-server'){
            agent any
            steps{
              echo 'Packaging worker app with docker'
              script{
                docker.withRegistry('https://index.docker.io/v1/', 'dockerlogin') {
                    def workerImage = docker.build("myselfakshatha/cmad-syslog-server:v${env.BUILD_ID}", "./syslog-server")
                    workerImage.push()
                    workerImage.push("latest")
                    }
                  } 
              }
          }
        }
      }
      
      stage('client'){
        // when{
          // changeset "**/syslog-client/**"
        // }
        stages{
          stage('client-build') {
              agent{
                      docker{
                      image 'node:10.9-alpine'
                    //   args '-v $HOME/node_modules:/root/node_modules'
                      }
            }
              steps {
                  echo 'compile syslog client service'
                  dir('syslog-client'){
                    sh 'npm install'
                    sh 'npm run-script build'
                    sh 'ls -la'
                    archiveArtifacts artifacts: '**/dist/*.*', fingerprint: true
                  }

              }
            }
            
          stage('client-test') {
              agent{
                      docker{
                      image 'node:10.9-alpine'
                      args '-v $HOME/node_modules:/root/node_modules'
                      }
            }
              steps {
                  echo 'run unit tests'
                  dir('syslog-client'){
                    sh 'npm install'
                  sh 'npm test'
                  }
                  
              }
            }
            
          
          stage('client-copy-artifact') {
              agent any
              steps{
                echo 'copying artifact'
                copyArtifacts filter: '**/dist/*.*', fingerprintArtifacts: true, projectName: '${JOB_NAME}', selector: specific('${BUILD_NUMBER}'), target: './syslog-client/'
              }
          }
          stage('client-docker-package'){
            agent any
            steps{
                echo 'Packaging syslog-client app with docker'
                script{
                  docker.withRegistry('https://index.docker.io/v1/', 'dockerlogin') {
                    def syslogClientImage = docker.build("myselfakshatha/cmad-syslog-client:v${env.BUILD_ID}", "./syslog-client/")
                    syslogClientImage.push()
                    syslogClientImage.push("latest")
                    }
                  }
                  
              }
          }
        }
      }

      stage('deploy-to-dev'){
        agent any
        when{
          branch 'master'
        }
        steps{
            echo 'Deploy the application'
            sh 'cd ..'
            sh 'docker-compose up -d'
        }
      }
    }
    
    post{
        always{
            echo "This pipeline run is completed.."
        }
    }
}

pipeline {
    agent any

    stages {

        stage ('Build' {
            steps {
                withMaven(maven: 'maven_3_5_0') {
                    sh 'mvn clean package'
                }
            }

            stage ('Deploy') {
                steps {

                    withCredentials([[$class: 'UsernamePasswordMultiBinding',
                                     credentialsId : 'pcf_login',
                                     usernameVariable : 'USERNAME',
                                     passwordVariable : 'PASSWORD']]) {
                        sh 'cf login -a http://api.run.pivotal.io -u $USERNAME -p $PASSWORD'
                        sh 'cf push'
                    }

                }
            }
        })
    }
}
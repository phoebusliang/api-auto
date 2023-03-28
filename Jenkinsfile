def main_resource

pipeline {
    agent any

    environment {
        PASSWORD = credentials('password')
    }

    stages {
        stage('Init Pipeline') {
            steps {
                script {
                    print "init pipeline"
                    main_resource = load 'main.groovy'

                }
            }
        }

        stage('Checkout Latest Code') {
            steps {
                checkout scm
            }
        }

        stage('Run Newman with Secret') {
            steps {
                script {
                    echo '***Create File and Folder***'
                    sh '''
                        ls
                        
                        mkdir test
                        mkdir test/newman
                        touch test/console.txt

                        ls
                    '''

                    echo '***Clean Up Report Directory***'
                    main_resource.cleanUp("test/newman", "test/console.txt")
                }
            }
        }
    }
}

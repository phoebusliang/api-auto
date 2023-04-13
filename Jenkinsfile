def main_resource

pipeline {
    agent any

    environment {
        PASSWORD = credentials('password')
        CUS_ENV = "sit"
        TEST_STR = "test-${CUS_ENV}"
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
                    echo "${TEST_STR}";
                    sh '''
                        ls test
                        if [ -d "test" ]; then
                            rm -rf test
                        fi
                        mkdir test
                        mkdir test/newman
                        touch test/console.txt

                        ls test
                    '''

                    echo '***Clean Up Report Directory***'
                    main_resource.cleanUp("test/newman", "test/console.txt")
                }
            }
        }
    }
}

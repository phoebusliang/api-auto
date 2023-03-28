def main_resource

pipeline {
    agent any

    environment {
        MY_SECRET_ENV_VAR = credentials('my-secret-text-id')
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

        stage('Install Dependencies') {
            steps {
                script {
                    sh '''
                        # Install Newman
                        sudo npm install -g newman

                        # Install jq
                        sudo apt-get update
                        sudo apt-get install -y jq

                        # Install Newman HTML reporter
                        sudo npm install -g newman-reporter-html

                        # Install Newman HTML reporter
                        sudo npm install -g newman-reporter-htmlextra
                    '''
                }
            }
        }


        stage('Run Newman with Secret') {
            steps {
                script {
                    echo '***Clean Up Report Directory***'
                    sh '''
                        if [ -d "newman" ]; then
                            rm -rf "newman"
                        fi

                        if [ -e "console.txt"]; then
                            rm "console.txt"
                        fi
                    '''


                    def collection_file = "NovaAPIs.postman_collection.json"
                    def environment_file = "nova.postman_environment.json"
                    def report_file = "newman_html_report.html"

                    sh '''
                        function run_newman_with_secret() {

                            cp "$environment_file" temp_nova.postman_environment.json
                            cp "$collection_file" temp_NovaAPIs.postman_collection.json

                            newman run temp_NovaAPIs.postman_collection.json -e temp_NovaAPIs.postman_collection.json -r cli,html --reporter-htmlextra-export "$report_file" --reporter-htmlextra-skipSensitiveData "token|password" --insecure > "console.txt"

                            rm temp_NovaAPIs.postman_collection.json temp_nova.postman_environment.json
                        }

                        run_newman_with_secret
                    '''
                }
            }
        }

        stage('Archive HTML Report') {
            steps {
                archiveArtifacts artifacts: 'newman_html_report.html', fingerprint: true
            }
        }

        stage('Send Email Notification') {
            steps {
                emailext(
                    to: 'team@example.com',
                    subject: "Newman Test Results: ${currentBuild.fullDisplayName}",
                    body: '''${FILE,path="newman_html_report.html"}
                                     Console output:
                                     ${BUILD_LOG, maxLines=1000}
                                  ''',
                    attachmentsPattern: 'newman_html_report.html',
                    mimeType: 'text/html'
                )
            }
        }
    }
}

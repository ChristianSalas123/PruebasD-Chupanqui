pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "MAVEN_HOME"
    }

    stages {
        stage('Clone') {
            steps {
                timeout(time: 2, unit: 'MINUTES'){
                    git branch: 'main', credentialsId: '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', url: 'https://github.com/ChristianSalas123/PruebasD-Chupanqui.git'
                }
            }
        }
        stage('Build') {
            steps {
                timeout(time: 12, unit: 'MINUTES'){
                    sh "mvn -DskipTests clean package -f chupanqui/pom.xml"
                }
            }
        }
        stage('Test') {
            steps {
                timeout(time: 12, unit: 'MINUTES'){
                    sh "mvn clean install -f chupanqui/pom.xml"
                }
            }
        }
       
	stage('Sonar') {
    	    steps {
        	timeout(time: 12, unit: 'MINUTES') {
            	     withSonarQubeEnv('sonarqube') {
                        sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar -Pcoverage -f chupanqui/pom.xml"
            	    }
                }
            }
        }

        stage('Quality gate') {
            steps {
                sleep(10) // seconds

                script {
                    try {
                        timeout(time: 15, unit: 'MINUTES') {
                            waitForQualityGate() // Sin abortPipeline para que no falle automáticamente
                        }
                    } catch (e) {
                        echo "Timeout o fallo del Quality Gate: ${e.getMessage()}, pero el pipeline continúa."
                        //ﬁﬁﬁ Si quieres marcar el stage como fallido pero continuar, puedes usar un buildResult
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        
        stage('Deploy') {
            steps {
        sh "mvn spring-boot:run -f chupanqui/pom.xml"
            }
        }
    }
}


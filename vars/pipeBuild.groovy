def mavenBuildAndTest(String cmd = "mvn clean package -DskipTests") {
    container("maven") {
        sh "${cmd}"
    }
}


def compile(cmd){
    container("compile") {
        sh cmd
    }
}

def buildDockerImage(String cmd) {
    container("dind") {
        sh cmd
    }
}

def pushDockerImageToECR(String cmd = "") {
    container("dind") {
        script {
            withAWS(credentials: 'aws-credentials', region: 'ap-southeast-1') {
                def ecrLoginCommand = ecrLogin(registryIds: ['746744850355'])
                sh "${ecrLoginCommand}"
                sh "docker tag spring-start-monitor:latest 746744850355.dkr.ecr.ap-southeast-1.amazonaws.com/spring-start-monitor:latest"
                sh "docker push 746744850355.dkr.ecr.ap-southeast-1.amazonaws.com/spring-start-monitor:latest"
            }
        }
    }
}


def deploy(String cmd = "") {
    container("helm") {
        sh 'kubectl apply -f kubernetes/deployment.yaml'
    }
}
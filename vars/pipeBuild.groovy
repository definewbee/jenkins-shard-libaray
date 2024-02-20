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


def deploy(String cmd = "") {
    container("helm") {
        sh cmd
    }
}
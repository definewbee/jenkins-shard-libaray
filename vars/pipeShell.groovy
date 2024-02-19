def nodebug(cmd) {
    def result = sh(returnStdout: true, script: "#!/bin/sh -e\n" + cmd)
    return result
}

def debug(cmd) {
    def result = sh(returnStdout: true, script: cmd)
    return result
}

def getStatus(cmd) {
    def result = sh(returnStatus: true, script: "#!/bin/sh -e\n" + cmd)
    return result
}
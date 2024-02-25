import com.wasai.devops.kubernetes.PodTemplate

def call(String name = "Java") {
    echo "Hello, project build Language: ${JOB_NAME}"

    String projectName = env.JOB_NAME.split('/')[0]
    //this.echo " project name is ------> ${projectName}"

    def jenkinsFilename = "file/${projectName}/Jenkinsfile"

    //this.echo " jenkins file name is ------> ${jenkinsFilename}"

    def jenkinsFile = libraryResource(jenkinsFilename)

    //this.echo " jenkins file is ------> ${jenkinsFile}"

    PodTemplate podTemplate = new PodTemplate(this)

    // 临时数据
    // 应该是来源于配置数据
    String baseImage = "maven:3.8.5"
    Boolean enablePipeline = true

    if (enablePipeline) {
        podTemplate.maven(baseImage) {
            ws("$workspace") {
                steps.cleanWs notFailBuild: true
                writeFile file: jenkinsFilename, text: jenkinsFile
                load(jenkinsFilename)
            }
        }
    } else {
        currentBuild.result = "ABORTED"
        error "Project pipeline disabled! skip..."
    }

}
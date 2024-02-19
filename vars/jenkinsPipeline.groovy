import com.wasai.devops.kubernetes.PodTemplate

def call(String name = "Java") {
    echo "Hello, project build Language: ${JOB_NAME}"

    String projectName = env.JOB_NAME.split('/')[0]
//    echo "${projectName}"
//    steps.echo "${projectName}"
    this.echo "${projectName}"

    def jenkinsFilename = "file/${projectName}/Jenkinsfile"
    def jenkinsFile = libraryResource(jenkinsFilename)

//    echo("execute java project")
    PodTemplate podTemplate = new PodTemplate(this)

    // 临时数据
    // 应该是来源于配置数据
    String baseImage = "maven:3.9"
    Boolean enablePipeline = true

    if (enablePipeline) {
        podTemplate.maven(baseImage) {
            ws("$workspace") {
                steps.cleanWs notFailBuild: true

                // load "file/demo1/Jenkinsfile"
                writeFile file: jenkinsFilename, text: jenkinsFile
                load(jenkinsFilename)
            }
        }
    } else {
        currentBuild.result = "ABORTED"
        error "Project pipeline disabled! skip..."
    }

}
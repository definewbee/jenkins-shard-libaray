package com.wasai.devops.tools

// 实现 Serializable 接口是为了确保当 jenkinsPipeline 被 Jenkins挂起后能正确恢复
class Pipeline implements Serializable {

    def createVersion(String BUILD_NUMBER) {
        println(env)
        return new Date().format('yyyyMM') + "-${BUILD_NUMBER}"
    }

}
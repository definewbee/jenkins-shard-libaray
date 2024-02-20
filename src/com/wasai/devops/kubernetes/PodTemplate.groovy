package com.wasai.devops.kubernetes

class PodTemplate {

    def steps

    // hub.mxcn.top/devops-ee/inbound-agent:3206.vb_15dcf73f6a_9-3
//    public static String JNLP_IMAGE = "library/inbound-agent:3206.vb_15dcf73f6a_9-3"
    public static String JNLP_IMAGE = "jenkins/inbound-agent:3107.v665000b_51092-15"
    public static String TmpShareDirectory = "/share/directory"

    public static String MavenRepositoryPVCName = "maven-repository-pvc-name"

    public static String MavenSettingSecretName = "maven-setting-secret-name"

    PodTemplate(steps) {
        this.steps = steps
    }

    String getJnlpImage() {
        String imageTag = JNLP_IMAGE
        if (steps.env.JKSJnlpImage != null) {
            imageTag = "${steps.env.JKSJnlpImage}"
            this.echo "Jenkins JNLP image: ${imageTag}";
        }
        return imageTag
    }

    public void maven(String baseImage, Closure body) {
        String jnlpImage = getJnlpImage()
        String kubeImage = "definewbie/jenkins:v0.3"
        String dind = "docker:dind"
        String helmImage = "alpine/k8s:1.26.14"

        steps.podTemplate(
            name: 'jks-slave-maven',
            cloud: 'kubernetes',
            label: baseImage,
            idleMinutes: 5,
            containers: [
                // maven image
                steps.containerTemplate(
                    name: 'maven', image: baseImage, command: 'sleep', args: "99999", ttyEnabled: true, alwaysPullImage: true,
                ),
                // kubectl cli
                steps.containerTemplate(
                    name: 'kubectl', image: kubeImage, command: 'sleep', args: "99999", ttyEnabled: true, alwaysPullImage: true,
                ),

                // jnlp
                steps.containerTemplate(
                    name: 'jnlp', image: jnlpImage, args: '${computer.jnlpmac} ${computer.name}', alwaysPullImage: true,
                ),

                steps.containerTemplate(
                    name: 'dind', image: dind, command: 'sleep', args: "99999", ttyEnabled: true, alwaysPullImage: true,
                ),

                steps.containerTemplate(
                    name: 'helm', image: helmImage, command: 'sleep', args: "99999", ttyEnabled: true, alwaysPullImage: true,
                )
            ],
            showRawYaml: true,
            serviceAccount: 'kubectl-proxy',
            volumes: [
                // 用于存储临时制品
                steps.emptyDirVolume(mountPath: TmpShareDirectory, memory: false),
                // 用于缓存
                // steps.persistentVolumeClaim(
                //         claimName: MavenRepositoryPVCName, mountPath: '/maven/repository'
                // ),
                // 配置jenkins默认settings.xml
                // steps.secretVolume(
                //         secretName: MavenSettingSecretName, mountPath: '/root/.m2'
                // ),
            ],
            imagePullSecrets: [
                "harbor-devops"
            ]
//                yaml: kubeYaml
        ) {
            steps.node(baseImage) {
                body()
            }
        }
    }
}

./
├── Jenkinsfile.example                                   jenkinsfile的DEMO样式
├── README.MD
├── jars
│   ├── groovy-cps-1.1.jar
│   └── pipeline-model-definition-1.7.1.jar
├── resources                                              配置文件和基础库分开管理了，所以这里不会存在静态资源文件
├── src
│   └── com
│       └── company
│           └── jenkins
│               ├── jenkins.com.wasai.devops.BasePipeline.groovy                    基础管道
│               ├── CommonPipeline.groovy                  公共管道
│               ├── Constant.groovy                        常量类
│               ├── Deploy.groovy                          部署基类
│               ├── Git.groovy                             git相关的操作类
│               ├── GolangPipeline.groovy                  golang项目管道
│               ├── LaravelPipeline.groovy                 laravel项目管道
│               ├── MPipeline.groovy                       初始化类
│               ├── MetlNotify.groovy                      用于发送通知的类
│               ├── Repo.groovy                            用于实例化代码仓库信息
│               ├── SummaryNotify.groovy                   用于结构化通知内容的类
│               └── deployments                            具体的部署类
│                   ├── AbstractDeployment.groovy          抽象部署类
│                   ├── BaseDeployment.groovy              基础部署类
│                   ├── DefaultDeployment.groovy           默认部署类
│                   └── GolangDeployment.groovy            Go部署类
├── tests                                                  单元测试目录
│   ├── RepoTest.groovy
│   └── classfiles
│       └── com
│           └── company
│               └── jenkins
│                   ├── Constant.class
│                   ├── Git.class
│                   ├── MPipeline.class
│                   ├── MetlNotify.class
│                   ├── Repo.class
│                   └── SummaryNotify.class
└── vars                                                   全局函数定义目录
    ├── mpipeline.groovy                                   自定义入口语法糖
    └── notify.groovy                                      定义通知全局调用函数
## Jenkins-shard-libaray

这是一个jenkins中的共享类库，只需要在jenkinsfile中对其引入，就可以引用共享库的代码，从而实现我们的复用。

但是有一点是这个共享库，需要我们储备点groovy语言的知识，因为他是用groovy作为”外挂”的方式加载运行的。

我们在写共享库的时候，必须 依照要有2个基本目录，否则jenkins无法类库。

- src (必要，核心代码)
- vars (非必要，声明自己的语法)
- resources (非必要，配置存放的目录)

```text
`build_user` 构建人
`build_branch` 构建分支
`build_env` 环境
`duration` 耗时
`build_number` 构建的number号
`build_status` 构建成功与否状态
`create_time` 构建开始时间
`update_time` 构建结束时间
`build_step` 构建步骤 checkout ? build ? 之类
`service_name` 服务名，用于填写的即可
`service_version` 版本，对应tag
`branch_type` 分支类型，来源于branch 还是 tag
```

```json
{
    "text": "From jenskins [Report] multidemoproj/v10.10.10#9",
    "buildUser": "shawn",
    "buildBranch": "v10.10.10",
    "buildNumber": "9",
    "buildEnv": "test",
    "duration": 16.957,
    "buildStatus": 1,
    "buildStep": "sleep_10_seconds",
    "stageName": "sleep_10_seconds",
    "serviceName": "multidemoproj/v10.10.10",
    "serviceVersion": "v10.10.10",
    "gitUrl": "https://code.pmxc.top/devops/demoproj.git",
    "commitID": "318d21b139a17e4c5c161040398682f01bf71a7d",
    "changeLog": "No changes",
    "timeInMillis": "1656169633646",
    "startTimeInMillis": "1656169633650",
    "currentResult": "SUCCESS",
    "durationString": "17 sec and counting",
    "previousBuild": "#8",
    "nextBuild": "null",
    "buildNodeName": "built-in"
}
```

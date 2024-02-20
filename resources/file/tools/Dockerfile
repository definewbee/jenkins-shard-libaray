# 使用官方AWS CLI版本2镜像作为基础镜像
FROM public.ecr.aws/aws-cli/aws-cli:latest

# 安装Kubectl
RUN curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" && \
    install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl

# 设置工作目录
WORKDIR /aws

# 运行时指令
CMD ["bash"]
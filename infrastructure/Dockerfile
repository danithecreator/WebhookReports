
FROM localstack/localstack:latest

RUN apt-get update && apt-get install -y \
    curl \
    unzip \
    && rm -rf /var/lib/apt/lists/*
RUN curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip" \
    && unzip awscliv2.zip \
    && ./aws/install \
    && rm -rf awscliv2.zip aws
RUN aws --version
RUN mkdir -p /root/.aws


RUN echo "[localstack]" > /root/.aws/config && \
    echo "region = us-east-1" >> /root/.aws/config && \
    echo "[localstack]" > /root/.aws/credentials && \
    echo "aws_access_key_id = test" >> /root/.aws/credentials && \
    echo "aws_secret_access_key = test" >> /root/.aws/credentials

COPY setup_infrastructure.sh /etc/localstack/init/ready.d/localstack-setup.sh

RUN chmod +x /etc/localstack/init/ready.d/localstack-setup.sh

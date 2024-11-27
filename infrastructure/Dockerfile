
FROM localstack/localstack:latest

# Instala dependencias para instalar AWS CLI
RUN apt-get update && apt-get install -y \
    curl \
    unzip \
    && rm -rf /var/lib/apt/lists/*

# Descarga e instala AWS CLI v2
RUN curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip" \
    && unzip awscliv2.zip \
    && ./aws/install \
    && rm -rf awscliv2.zip aws

# Verifica que AWS CLI esté instalado
RUN aws --version

# Crea directorios para el AWS CLI
RUN mkdir -p /root/.aws

# Archivo de configuración de perfiles
RUN echo "[localstack]" > /root/.aws/config && \
    echo "region = us-east-1" >> /root/.aws/config && \
    echo "[localstack]" > /root/.aws/credentials && \
    echo "aws_access_key_id = test" >> /root/.aws/credentials && \
    echo "aws_secret_access_key = test" >> /root/.aws/credentials

# Copia el script de configuración a LocalStack
COPY setup_infrastructure.sh /etc/localstack/init/ready.d/localstack-setup.sh

# Asegúrate de que el script sea ejecutable
RUN chmod +x /etc/localstack/init/ready.d/localstack-setup.sh

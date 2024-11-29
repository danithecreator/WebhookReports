FROM python:3.12

WORKDIR /code

COPY ./requirements.txt /code/requirements.txt

RUN pip install --no-cache-dir --upgrade -r /code/requirements.txt

ENV AWS_ACCESS_KEY_ID=test
ENV AWS_SECRET_ACCESS_KEY=test
ENV AWS_DEFAULT_REGION=us-east-1

COPY ./getApiURL.py /code
COPY ./worker.py /code
COPY ./config.py /code
COPY ./providers /code/providers
COPY validateHealth.sh /code/validateHealth.sh
RUN touch /code/.env
RUN chmod 666 /code/.env
RUN chmod +x /code/validateHealth.sh

# Establecer el script de entrada
ENTRYPOINT ["/code/validateHealth.sh"]


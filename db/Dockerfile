FROM postgres:latest
ENV POSTGRES_DB=webhook
ENV POSTGRES_USER=user
ENV POSTGRES_PASSWORD=password

COPY init.sql /docker-entrypoint-initdb.d/

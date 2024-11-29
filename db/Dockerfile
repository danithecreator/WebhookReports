FROM postgres:latest
ENV POSTGRES_DB=webhook
ENV POSTGRES_USER=user
ENV POSTGRES_PASSWORD=password

COPY backup.sql /docker-entrypoint-initdb.d/

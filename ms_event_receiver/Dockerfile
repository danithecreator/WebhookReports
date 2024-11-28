FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

ENV AWS_ACCESS_KEY_ID=test
ENV AWS_SECRET_ACCESS_KEY=test
ENV AWS_DEFAULT_REGION=us-east-1

COPY applications/app-service/build/libs/ms_event_receiver.jar ms_event_receiver.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "ms_event_receiver.jar"]
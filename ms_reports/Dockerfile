FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY applications/app-service/build/libs/ms_reports.jar ms_reports.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "ms_reports.jar"]
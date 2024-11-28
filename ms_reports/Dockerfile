FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY applications/app-service/build/libs/ms_reports.jar ms_reports.jar

EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "ms_reports.jar"]
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY applications/app-service/build/libs/ms_event_receiver.jar ms_event_receiver.jar

EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "ms_event_receiver.jar"]
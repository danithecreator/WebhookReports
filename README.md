# Solución para el Procesamiento de Eventos Webhook

## Descripción General
Este proyecto proporciona una solución local para coordinar múltiples servicios y emular cómo deben procesarse los eventos generados por proveedores de webhooks, con el objetivo de generar reportes que aporten valor a la organización.

## Componentes del Proyecto

### Generador de Eventos
Una aplicación en Python que simula el comportamiento de diferentes proveedores de webhooks emitiendo eventos en formato JSON de manera infinita. Este script fue proporcionado como parte del reto y se modificó para adaptarlo a la solución propuesta. Inicialmente, esta aplicación utilizaba WebSockets para enviar eventos en formato JSON, pero se modificó para que usara peticiones HTTP dirigidas a un endpoint proporcionado por un API Gateway.

### Infraestructura
La infraestructura incluye una imagen de LocalStack aprovisionada mediante un script personalizado. Este script configura un API Gateway integrado con una cola SQS. La aplicación en Python envía eventos a las URL expuestas por el API Gateway, que luego los redirige a la cola SQS.

### MS Event Receiver
Este microservicio, desarrollado con Spring WebFlux, lee mensajes de la cola SQS, aplica transformaciones para extraer información relevante y guarda los datos procesados en una base de datos RDS.

### MS Reports
Otro microservicio desarrollado con Spring WebFlux que expone una API REST para generar los reportes solicitados.

### Base de Datos
Una base de datos RDS PostgreSQL utilizada para persistir los datos capturados y procesados de los eventos.

### Orquestación
Todo el flujo se orquesta mediante un archivo `docker-compose`.

## Instrucciones de Ejecución

Para ejecutar el proyecto, siga estas recomendaciones:

1. **Evitar conflictos con códigos de control:**
   Antes de clonar el proyecto, ejecute el siguiente comando:
   ```bash
   git config --global core.autocrlf false
   ```

2. **Construir los microservicios:**
   - Navegue a la carpeta `ms_reports` y ejecute:
     ```bash
     gradle clean build
     ```
   - Navegue a la carpeta `ms_event_receiver` y ejecute:
     ```bash
     gradle clean build
     ```

3. **Ejecutar los servicios:**
   - Navegue a la carpeta `compose` y ejecute:
     ```bash
     docker compose build
     docker compose up
     ```

   Esto iniciará la ejecución del proyecto.

4. **Generar reportes:**
   Se proporciona una colección con los endpoints API necesarios para facilitar la generación de reportes.


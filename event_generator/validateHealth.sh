#!/bin/bash

# Esperar a que LocalStack esté disponible usando SQS
echo "Esperando que LocalStack esté disponible..."
until curl -s http://localstack:4566/_localstack/health | grep "running"; do
    sleep 5
done

# Ejecutar el script para obtener las URLs de LocalStack y configurar las variables de entorno

# Ejecutar el contenedor principal (worker.py)
echo "Ejecutando worker.py..."
exec python -u worker.py
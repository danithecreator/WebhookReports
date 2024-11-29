#!/bin/bash

echo "Esperando que LocalStack esté disponible..."
until curl -s http://localstack:4566/_localstack/health | grep "running"; do
    sleep 25
done

python /code/getApiURL.py
echo "Ejecutando worker.py..."
sleep 15
exec python -u worker.py
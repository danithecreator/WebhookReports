#!/bin/bash

# Endpoint y perfil de LocalStack
ENDPOINT_URL="http://localhost:4566"
PROFILE="localstack"

# Eliminar todas las colas SQS
echo "Listando todas las colas SQS..."
QUEUE_URLS=$(aws sqs list-queues --query 'QueueUrls' --output text --profile $PROFILE --endpoint-url=$ENDPOINT_URL)

if [ -z "$QUEUE_URLS" ]; then
  echo "No se encontraron colas SQS."
else
  echo "Colas SQS encontradas: $QUEUE_URLS"
  for QUEUE_URL in $QUEUE_URLS
  do
    echo "Eliminando cola SQS: $QUEUE_URL"
    aws sqs delete-queue --queue-url $QUEUE_URL --profile $PROFILE --endpoint-url=$ENDPOINT_URL
  done
  echo "Todas las colas SQS han sido eliminadas."
fi

# Listar todos los API Gateways
echo "Listando todos los API Gateways..."
API_IDS=$(aws apigateway get-rest-apis --query 'items[*].id' --output text --profile $PROFILE --endpoint-url=$ENDPOINT_URL)

if [ -z "$API_IDS" ]; then
  echo "No se encontraron API Gateways."
else
  echo "API Gateways encontrados: $API_IDS"
  for API_ID in $API_IDS
  do
    echo "Eliminando API Gateway con ID: $API_ID"
    aws apigateway delete-rest-api --rest-api-id $API_ID --profile $PROFILE --endpoint-url=$ENDPOINT_URL
  done
  echo "Todos los API Gateways han sido eliminados."
fi

echo "Proceso de limpieza completado."

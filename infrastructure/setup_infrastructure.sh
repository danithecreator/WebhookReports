#!/bin/bash


API_NAME="WebhookAPI"
REGION="us-east-1"
ENDPOINT_URL="http://localhost:4566"
PROFILE="localstack"
QUEUE_NAME="WebhookQueue"

QUEUE_URL=$(aws sqs create-queue \
  --queue-name $QUEUE_NAME \
  --region $REGION \
  --endpoint-url=$ENDPOINT_URL \
  --profile $PROFILE \
  --attributes '{"VisibilityTimeout":"30"}' \
  --query 'QueueUrl' --output text)
echo "Cola SQS creada con URL: $QUEUE_URL"


QUEUE_ARN=$(aws sqs get-queue-attributes \
  --queue-url $QUEUE_URL \
  --attribute-names QueueArn \
  --region $REGION \
  --endpoint-url=$ENDPOINT_URL \
  --profile $PROFILE \
  --query 'Attributes.QueueArn' --output text)
echo "ARN de la cola SQS: $QUEUE_ARN"

API_ID=$(aws apigateway create-rest-api \
  --name "$API_NAME" \
  --region $REGION \
  --endpoint-url=$ENDPOINT_URL \
  --profile $PROFILE \
  --query 'id' --output text)
echo "API Gateway creado con ID: $API_ID"

ROOT_RESOURCE_ID=$(aws apigateway get-resources \
  --rest-api-id $API_ID \
  --region $REGION \
  --endpoint-url=$ENDPOINT_URL \
  --profile $PROFILE \
  --query 'items[?path==`"/"`].id' --output text)
echo "Root Resource ID: $ROOT_RESOURCE_ID"

for RESOURCE in "commit" "pipeline" "pullrequest"
do
  RESOURCE_ID=$(aws apigateway create-resource \
    --rest-api-id $API_ID \
    --parent-id $ROOT_RESOURCE_ID \
    --path-part $RESOURCE \
    --region $REGION \
    --endpoint-url=$ENDPOINT_URL \
    --profile $PROFILE \
    --query 'id' --output text)



  aws apigateway put-method \
    --rest-api-id $API_ID \
    --resource-id $RESOURCE_ID \
    --http-method POST \
    --authorization-type "NONE" \
    --region $REGION \
    --endpoint-url=$ENDPOINT_URL \
    --profile $PROFILE


  aws apigateway put-integration \
    --rest-api-id $API_ID \
    --resource-id $RESOURCE_ID \
    --http-method POST \
    --type AWS \
    --integration-http-method POST \
    --uri "arn:aws:apigateway:us-east-1:sqs:path/000000000000/${QUEUE_NAME}" \
    --request-parameters '{"integration.request.header.Content-Type":"'\''application/x-www-form-urlencoded'\''"}' \
    --request-templates '{"application/json":"Action=SendMessage&MessageBody={\"resource\":\"'"${RESOURCE}"'\",\"timestamp\":\"$context.requestTime\",\"body\":$input.json(\"$\")}","application/x-www-form-urlencoded":"Action=SendMessage&MessageBody={\"resource\":\"'"${RESOURCE}"'\",\"body\":$input.body}"}' \
    --region $REGION \
    --endpoint-url=$ENDPOINT_URL \
    --profile $PROFILE



  aws apigateway put-method-response \
    --rest-api-id $API_ID \
    --resource-id $RESOURCE_ID \
    --http-method POST \
    --status-code 200 \
    --region $REGION \
    --endpoint-url=$ENDPOINT_URL \
    --profile $PROFILE


  aws apigateway put-integration-response \
    --rest-api-id $API_ID \
    --resource-id $RESOURCE_ID \
    --http-method POST \
    --status-code 200 \
    --selection-pattern "" \
    --response-templates '{"application/json":"{\"status\":\"message sent to SQS\"}"}' \
    --region $REGION \
    --endpoint-url=$ENDPOINT_URL \
    --profile $PROFILE
done


DEPLOYMENT_ID=$(aws apigateway create-deployment \
  --rest-api-id $API_ID \
  --stage-name "dev" \
  --region $REGION \
  --endpoint-url=$ENDPOINT_URL \
  --profile $PROFILE \
  --query 'id' --output text)
echo "Despliegue creado con ID: $DEPLOYMENT_ID en el entorno 'dev'"


API_BASE_URL="$ENDPOINT_URL/restapis/$API_ID/dev/_user_request_"
echo "API Gateway desplegado en: $API_BASE_URL"




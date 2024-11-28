import boto3
from dotenv import load_dotenv, set_key
import os

env_file = '/code/.env'
if not os.path.exists(env_file):
    raise FileNotFoundError(f"El archivo {env_file} no se encontró. Por favor, verifica la ruta.")

load_dotenv(env_file)
try:
    client = boto3.client(
        'apigateway',
        endpoint_url='http://localstack:4566',
        region_name='us-east-1'
    )
except Exception as e:
    raise ConnectionError("No se pudo conectar con LocalStack. Verifica que esté ejecutándose.") from e

try:
    response = client.get_rest_apis()
    api_id = response['items'][0]['id'] if 'items' in response and len(response['items']) > 0 else None
except Exception as e:
    raise RuntimeError("Error al intentar obtener las APIs de LocalStack.") from e

if api_id:
    print(f"Nuevo API ID: {api_id}")
    try:
        set_key(env_file, 'AG_COMMITS', f'http://localstack:4566/restapis/{api_id}/dev/_user_request_/commit')
        set_key(env_file, 'AG_PULLREQUEST', f'http://localstack:4566/restapis/{api_id}/dev/_user_request_/pullrequest')
        set_key(env_file, 'AG_PIPELINE', f'http://localstack:4566/restapis/{api_id}/dev/_user_request_/pipeline')
        print(f"Archivo {env_file} actualizado con el nuevo API ID.")
        load_dotenv(env_file)
    except Exception as e:
        raise RuntimeError(f"Error al intentar actualizar el archivo {env_file}.") from e
else:
    print("No se encontraron APIs disponibles en LocalStack.")

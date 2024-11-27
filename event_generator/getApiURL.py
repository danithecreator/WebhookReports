import boto3
from dotenv import load_dotenv, set_key
import os

# Validar que el archivo .env exista antes de cargarlo
env_file = '/code/.env'
if not os.path.exists(env_file):
    raise FileNotFoundError(f"El archivo {env_file} no se encontró. Por favor, verifica la ruta.")

# Carga las variables de entorno desde el archivo .env
load_dotenv(env_file)

# Configura el cliente de API Gateway para LocalStack
try:
    client = boto3.client(
        'apigateway',
        endpoint_url='http://localstack:4566',  # URL del endpoint de LocalStack
        region_name='us-east-1'  # Región de AWS
    )
except Exception as e:
    raise ConnectionError("No se pudo conectar con LocalStack. Verifica que esté ejecutándose.") from e

# Intenta obtener las APIs REST de LocalStack
try:
    response = client.get_rest_apis()
    # Extrae el ID del primer API
    api_id = response['items'][0]['id'] if 'items' in response and len(response['items']) > 0 else None
except Exception as e:
    raise RuntimeError("Error al intentar obtener las APIs de LocalStack.") from e

if api_id:
    print(f"Nuevo API ID: {api_id}")
    try:
        # Actualiza las variables en el archivo .env
        set_key(env_file, 'AG_COMMITS', f'http://localstack:4566/restapis/{api_id}/dev/_user_request_/commit')
        set_key(env_file, 'AG_PULLREQUEST', f'http://localstack:4566/restapis/{api_id}/dev/_user_request_/pullrequest')
        set_key(env_file, 'AG_PIPELINE', f'http://localstack:4566/restapis/{api_id}/dev/_user_request_/pipeline')
        print(f"Archivo {env_file} actualizado con el nuevo API ID.")
    except Exception as e:
        raise RuntimeError(f"Error al intentar actualizar el archivo {env_file}.") from e
else:
    print("No se encontraron APIs disponibles en LocalStack.")

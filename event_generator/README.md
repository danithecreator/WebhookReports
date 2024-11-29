# Event generation microservice

This is a small microservice that will emit fake events to imitate those emitted by GitHub and Azure when a user subscribes to their events Webhook. This is given for your convenience, you are allowed to modify this as you see fit, but you must explain/document all of those changes. You are NOT required to use this microservice in your solution, you can build your own event generator if you prefer, nor are you required to modify it.

The microservice can generate the following events:

- GitHub commit push event
- GitHub pull request closed event
- GitHub Actions workflow execution finished (either successful or failed)
- Azure Repos commit push event
- Azure Repos pull request closed event
- Azure Build pipeline  execution finished (either successful or failed)

The microservice is made as a vanilla Python project with no framework. It can be used either as a pre-built Docker image, or by running it locally with Python. Either way, you must configure some environment variables.

Additionally, a very simple WebSocket server is provided to allow you to test the microservice. The file is called `websocket-server.py`. The WebSocket server will listen for connections on port 8765 and will accept connections from the microservice to emit the fake events. This is given just for testing convenience, and is not a necessary part of the challenge.

## Configuration

All the enviroment variables required by the microservice are specificed in the `.env.example` file. You can duplicate that file and rename it to `.env` to modify as needed. The microservice will automatically attempt to load any `.env` file that exists within the project.

### Environment Variables

- `WS_GH_REPOS`: This variable specifies the WebSocket URL for GitHub repository events. The microservice will connect to this URL to emit fake GitHub repository events.
- `WS_GH_ACTIONS`: This variable specifies the WebSocket URL for GitHub Actions events. The microservice will connect to this URL to emit fake GitHub Actions workflow events.
- `WS_AZ_REPOS`: This variable specifies the WebSocket URL for Azure Repos events. The microservice will connect to this URL to emit fake Azure Repos commit push events.
- `WS_AZ_ACTIONS`: This variable specifies the WebSocket URL for Azure Actions events. The microservice will connect to this URL to emit fake Azure Build pipeline events.
- `AVG_TIME_BETWEEN_EVENTS_MS`: This variable sets the average time (in milliseconds) between the generation of consecutive events. It controls the frequency of event generation.
- `STD_DV_TIME_BETWEEN_EVENTS_MS`: This variable sets the standard deviation (in milliseconds) for the time between the generation of consecutive events. It adds variability to the event generation frequency.
- `GITHUB_REPOS_ENABLED`: This boolean variable enables or disables the generation of GitHub repository events. Set it to `true` to enable or `false` to disable.
- `GITHUB_ACTIONS_ENABLED`: This boolean variable enables or disables the generation of GitHub Actions events. Set it to `true` to enable or `false` to disable.
- `AZURE_REPOS_ENABLED`: This boolean variable enables or disables the generation of Azure Repos events. Set it to `true` to enable or `false` to disable.
- `AZURE_ACTIONS_ENABLED`: This boolean variable enables or disables the generation of Azure Actions events. Set it to `true` to enable or `false` to disable

### Creating a virtual environment

If you want to run the local project using a Python virtual environment, please refer to the following guide: [venv](https://docs.python.org/3/library/venv.html).

A `requirements.txt` file is provided with the required dependencies.

### Running the project

#### Using Docker

To run the project using Docker, you can pull and run the pre-built image from GitHub Container Registry:

```bash
docker run --env-file .env --net=host --name retobancolombia ghcr.io/retobancolombia/eventgenerator:main
```

> [!WARNING]
> If you are using WSL, running the Docker container with `--net=host` and running the test server locally from within Windows, you may need to use the IP address of the host machine instead of `localhost` in the `.env` file websocket URLs. You can run `echo "$(hostname).local"` in the WSL terminal to get the hostname to replace `localhost` with. [More info can be found here](https://superuser.com/questions/1679757/accessing-windows-localhost-from-wsl2/1835289#1835289)

If you want to build the image yourself, you can use the provided `Dockerfile`:

```bash
docker build -t eventgenerator .
docker run --env-file .env  --net=host --name retobancolombia eventgenerator
```

#### Locally

You must have a `.env` file with the required environment variables. Then you must configure your local Python enviroment or virtual enviroment with the required dependencies specified in `requirements.txt`.

Then, you can run the project with Python:

```bash
python worker.py
```

The testing WebSocket server can be run with Python as well:

```bash
python websocket-server.py
```

It will output logs to both the console and `websocket_server.log`.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

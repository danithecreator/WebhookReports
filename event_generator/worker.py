import os
import random
import time

import dotenv
from config import Config

from providers.Azure import AzureProvider
from providers.Github import GithubProvider


def worker():
    # Load environment variables
    dotenv.load_dotenv()

    config = Config()

    if os.getenv("DEBUG", "false").lower() == "true":
        print(config)

    print("Worker started")
    time.sleep(5)

    platform_bag = []

    if config.GITHUB_REPOS_ENABLED:
        print("GitHub repos enabled")
        platform_bag.append("github_repos")
    if config.GITHUB_ACTIONS_ENABLED:
        print("GitHub Actions enabled")
        platform_bag.append("github_actions")
    if config.AZURE_REPOS_ENABLED:
        print("Azure Repos enabled")
        platform_bag.append("azure_repos")
    if config.AZURE_ACTIONS_ENABLED:
        print("Azure Actions enabled")
        platform_bag.append("azure_actions")

    while True:
        # Sleep for a random time
        sleep_time = random.gauss(config.AVG_TIME_BETWEEN_EVENTS_MS, config.STD_DV_TIME_BETWEEN_EVENTS_MS) / 1000
        time.sleep(sleep_time)
        gh_provider = GithubProvider(config)
        az_provider = AzureProvider(config)
        # Choose a random platform
        platform = random.choice(platform_bag)
        # if platform == "github":
            # gh_provider.generate()
        if platform == "azure_repos":
            az_provider.generate(["push","pull_request.closed"], [0.7, 0.3])
        elif platform == "azure_actions":
            az_provider.generate(["pipeline_executed"], [1.0])
        elif platform == "github_repos":
            gh_provider.generate(["push","pull_request.closed"], [0.7, 0.3])
        elif platform == "github_actions":
            gh_provider.generate(["pipeline_executed"], [1.0])


worker()
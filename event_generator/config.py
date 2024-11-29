import os

class Config:
    def __init__(self):
        self.AG_COMMITS = os.getenv("AG_COMMITS")
        self.AG_PULLREQUEST = os.getenv("AG_PULLREQUEST")
        self.AG_PIPELINE = os.getenv("AG_PIPELINE")
        self.AVG_TIME_BETWEEN_EVENTS_MS = int(os.getenv("AVG_TIME_BETWEEN_EVENTS_MS"))
        self.STD_DV_TIME_BETWEEN_EVENTS_MS = int(os.getenv("STD_DV_TIME_BETWEEN_EVENTS_MS"))
        self.GITHUB_REPOS_ENABLED = os.getenv("GITHUB_REPOS_ENABLED").lower() == "true"
        self.GITHUB_ACTIONS_ENABLED = os.getenv("GITHUB_ACTIONS_ENABLED").lower() == "true"
        self.AZURE_REPOS_ENABLED = os.getenv("AZURE_REPOS_ENABLED").lower() == "true"
        self.AZURE_ACTIONS_ENABLED = os.getenv("AZURE_ACTIONS_ENABLED").lower() == "true"
        self.REPOSITORIES_IDS  = ["33d3b559-dde4-4e8e-b8c9-b549333be59f","d2e4a557-ade3-4b9c-9e8d-b549333ae17f","a1f3b458-cce2-4e7b-8d7c-b549333bf58f","e4c3a356-dce5-4f9c-9f9e-b549333ad98f","c2f3b459-ede6-4d8b-8e7d-b549333ac39f","b3e3a457-fde7-4c9a-9f6e-b549333aa47f","f2d3b451-gde8-4b7d-8d6c-b549333a859f","d1c3a352-hde9-4f8c-9f5e-b549333a279f","e3f3b454-ide0-4d7b-8e5d-b549333a149f","a4e3a353-jde1-4c9a-9e4c-b549333a939f"]

    def __str__(self):
        return (
            f"Config("
            f"AG_COMMITS={self.AG_PIPELINE}, "
            f"AG_PULLREQUEST={self.AG_PULLREQUEST}, "
            f"AG_PIPELINE={self.AG_PIPELINE}, "
            f"AVG_TIME_BETWEEN_EVENTS_MS={self.AVG_TIME_BETWEEN_EVENTS_MS}, "
            f"STD_DV_TIME_BETWEEN_EVENTS_MS={self.STD_DV_TIME_BETWEEN_EVENTS_MS}, "
            f"GITHUB_REPOS_ENABLED={self.GITHUB_REPOS_ENABLED}, "
            f"GITHUB_ACTIONS_ENABLED={self.GITHUB_ACTIONS_ENABLED}, "
            f"AZURE_REPOS_ENABLED={self.AZURE_REPOS_ENABLED}, "
            f"AZURE_ACTIONS_ENABLED={self.AZURE_ACTIONS_ENABLED}, "
            f"REPOSITORIES_IDS={self.REPOSITORIES_IDS})"
        )
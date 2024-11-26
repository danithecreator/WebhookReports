import datetime
import json
import random

import faker
from faker import Faker
import requests 

from providers.generator import Generator

fake = Faker()


class AzureProvider(Generator):
    def __init__(self, config):
        super().__init__(config)
        
        self.api_url_commit = f"{self.config.AG_COMMITS}"
        self.api_url_pull_request = f"{self.config.AG_PULLREQUEST}"
        self.api_url_pipeline = f"{self.config.AG_PIPELINE}"
        self.repositories_ids = self.config.REPOSITORIES_IDS

        self.repositories = self._generate_repositories()

    def generate(self, choices: list[str], weights: list):
        event_type = random.choices(
            choices,
            weights,
            k=1,
        )[0]
        if event_type == "push":
            self._generate_commit_event()
        elif event_type == "pipeline_executed":
            self._generate_pipeline_execution_event()
        elif event_type == "pull_request.closed":
            self._generate_pull_request_closed_event()

    def _generate_pull_request_closed_event(self):
        closed_date = datetime.datetime.now(tz=datetime.timezone.utc)
        creation_date = closed_date - datetime.timedelta(hours=random.randint(1, 100))
        repository = self.repositories[random.randint(0, len(self.repositories) - 1)]

        body = {
            "repository": repository,
            "pullRequestId": random.randint(1, 1000000),
            "codeReviewId": random.randint(1, 1000000),
            "status": "completed",
            "creationDate": creation_date.isoformat().replace("+00:00", "Z"),
            "closedDate": closed_date.isoformat().replace("+00:00", "Z"),
            "title": fake.sentence(),
            "description": fake.paragraph(),
            "sourceRefName": f"refs/heads/{fake.word()}",
            "targetRefName": "refs/heads/trunk",
            "mergeStatus": "succeeded",
            "isDraft": False,
            "mergeId": fake.uuid4(),
            "reviewers": [
            {
                "displayName": fake.name(),
                "url": fake.url(),
                "id": fake.uuid4(),
                "uniqueName": fake.email(),
            }
            ],
            "url": fake.url(),
            "completionOptions": {
            "mergeCommitMessage": fake.sentence(),
            "deleteSourceBranch": True,
            "triggeredByAutoComplete": True,
            },
            "supportsIterations": True,
            "closedBy": {
            "displayName": fake.name(),
            "url": fake.url(),
            "_links": {
                "avatar": {
                "href": fake.url()
                }
            },
            "id": fake.uuid4(),
            "uniqueName": fake.email(),
            "imageUrl": fake.url(),
            "descriptor": fake.uuid4(),
            },
            "artifactId": f"vstfs:///Git/PullRequestId/{repository['project']['id']}%2f{repository['id']}%2f{random.randint(1, 1000000)}"
        }
        response = requests.post(self.api_url_pull_request, json=body)
        if response.status_code == 200:
            print("Evento pull request cerrado enviado correctamente.")
        else:
            print(f"Error al enviar evento pull request cerrado: {response.status_code}")

    def _generate_pipeline_execution_event(self):
        result_type = random.choice(["succeeded", "failed"])
        queue_time = datetime.datetime.now(
            tz=datetime.timezone.utc
        ) - datetime.timedelta(
            minutes=random.randint(0, 60 * 12), days=random.randint(0, 3 * 365)
        )
        start_time = queue_time + datetime.timedelta(seconds=random.randint(1, 60))
        finish_time = start_time + datetime.timedelta(
            seconds=random.randint(1, 10 * 60)
        )
        repository = self.repositories[random.randint(0, len(self.repositories) - 1)]

        body = {
            "id": random.randint(1, 10000000),
            "buildNumber": f"develop_{queue_time.strftime('%Y%m%d')}.{random.randint(1, 100)}",
            "status": "completed",
            "result": result_type,
            "queueTime": queue_time.isoformat().replace("+00:00", "Z"),
            "startTime": start_time.isoformat().replace("+00:00", "Z"),
            "finishTime": finish_time.isoformat().replace("+00:00", "Z"),
            "url": fake.url(),
            "project": {
                "id": fake.uuid4(),
                "name": fake.company(),
                "description": fake.catch_phrase(),
                "url": fake.url(),
                "state": "wellFormed",
                "revision": random.randint(1, 10000),
                "visibility": "private",
                "lastUpdateTime": queue_time.isoformat().replace("+00:00", "Z"),
            },
            "priority": "normal",
            "reason": "individualCI",
            "requestedFor": {
                "displayName": fake.name(),
                "id": fake.uuid4(),
            },
            "lastChangedDate": finish_time.isoformat().replace("+00:00", "Z"),
            "repository": repository,
            "retainedByRelease": False,
            "triggeredByBuild": None,
        }
        response = requests.post(self.api_url_pipeline, json=body)
        if response.status_code == 200:
            print("Evento pipeline ejecutado enviado correctamente.")
        else:
            print(f"Error al enviar evento pipeline ejecutado: {response.status_code}")

    def _generate_commit_event(self):
        push_date = datetime.datetime.now(tz=datetime.timezone.utc)

        body = {
            "repository": self.repositories[
                random.randint(0, len(self.repositories) - 1)
            ],
            "pushedBy": {
                "displayName": fake.name(),
                "url": "https://spsprodcus3.vssps.visualstudio.com/Ac3a0513d-f061-427c-b611-49ec0207b97a/_apis/Identities/44ded187-7ae8-6c62-9a17-c8433ac07f81",
                "_links": {
                    "avatar": {
                        "href": "https://grupobancolombia.visualstudio.com/_apis/GraphProfile/MemberAvatars/aad.NDRkZWQxODctN2FlOC03YzYyLTlhMTctYzg0MzNhYzA3Zjgx"
                    }
                },
                "id": fake.uuid4(),
                "uniqueName": fake.email(),
            },
            "pushId": fake.uuid4(),
            "date": push_date.isoformat().replace("+00:00", "Z"),
            "url": "https://grupobancolombia.visualstudio.com/_apis/git/repositories/f8b91dec-c0de-413d-8c41-788459da5b75/pushes/8552064",
            "commits": self._generate_commits(push_date),
        }
        response = requests.post(self.api_url_commit, json=body)
        if response.status_code == 200:
            print("Evento push commits enviado correctamente.")
        else:
            print(f"Error al enviar evento push commits: {response.status_code}")

    def _generate_repositories(self):
        repositories = []
        num_repos = 15
        for _ in range(1, num_repos):
            repositories.append(
                {
                    "id": random.choice(self.repositories_ids),
                    "name": f"AZ-{fake.word()}-{fake.word()}".upper(),
                    "url": fake.url(),
                    "project": {
                        "id": fake.uuid4(),
                        "name": fake.word(),
                        "url": fake.url(),
                    },
                }
            )
        return repositories

    def _generate_commits(self, end_date: datetime.datetime) -> list[dict]:
        commits = []
        commit_number = random.randint(1, 8)
        for _ in range(1, commit_number):
            commit_date = end_date - datetime.timedelta(
                minutes=random.randint(0, 60 * 12), days=random.randint(0, 3)
            )
            commits.append(
                {
                    "commitId": fake.uuid4(),
                    "author": {
                        "name": fake.name(),
                        "email": fake.email(),
                        "date": commit_date.isoformat().replace("+00:00", "Z"),
                    },
                    "committer": {
                        "name": fake.name(),
                        "email": fake.email(),
                        "date": commit_date.isoformat().replace("+00:00", "Z"),
                    },
                    "comment": fake.sentence(),
                    "url": fake.url(),
                }
            )
        return commits


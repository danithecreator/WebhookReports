import datetime
import json
import random

from faker import Faker
import requests 
from providers.generator import Generator

fake = Faker()

class GithubProvider(Generator):
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
        elif event_type == "pull_request.closed":
            self._generate_pull_request_closed_event()
            """ self._generate_commit_event() """
        elif event_type == "pipeline_executed":
            self._generate_workflow_run_event()

    def _generate_pull_request_closed_event(self):
        closed_date = datetime.datetime.now(tz=datetime.timezone.utc)
        creation_date = closed_date - datetime.timedelta(hours=random.randint(1, 100))
        repository = self.repositories[random.randint(0, len(self.repositories) - 1)]

        body = {
            "action": "closed",
            "number": random.randint(1, 1000),
            "pull_request": {
                "url": fake.url(),
                "id": random.randint(1, 1000000),
                "node_id": fake.uuid4(),
                "number": random.randint(1, 1000),
                "state": "closed",
                "locked": False,
                "title": fake.sentence(),
                "user": {
                    "login": fake.user_name(),
                    "id": random.randint(1, 1000000),
                    "node_id": fake.uuid4(),
                    "gravatar_id": "",
                    "url": fake.url(),
                    "type": "User",
                    "site_admin": False
                },
                "body": fake.paragraph(),
                "created_at": creation_date.isoformat().replace("+00:00", "Z"),
                "updated_at": closed_date.isoformat().replace("+00:00", "Z"),
                "closed_at": closed_date.isoformat().replace("+00:00", "Z"),
                "merged_at": closed_date.isoformat().replace("+00:00", "Z"),
                "merge_commit_sha": fake.sha1(),
                "assignee": None,
                "assignees": [],
                "requested_reviewers": [],
                "requested_teams": [],
                "labels": [],
                "milestone": None,
                "draft": False,
                "head": {
                    "label": f"{repository['full_name']}:develop",
                    "ref": "develop",
                    "sha": fake.sha1(),
                    "user": {
                        "login": repository['owner']['login'],
                        "id": repository['owner']['id'],
                        "node_id": repository['owner']['node_id'],
                        "gravatar_id": "",
                        "url": repository['owner']['url'],
                        "type": "Organization",
                        "site_admin": False
                    },
                    "repo": repository
                },
                "base": {
                    "label": f"{repository['full_name']}:main",
                    "ref": "main",
                    "sha": fake.sha1(),
                    "user": {
                        "login": repository['owner']['login'],
                        "id": repository['owner']['id'],
                        "node_id": repository['owner']['node_id'],
                        "gravatar_id": "",
                        "url": repository['owner']['url'],
                        "type": "Organization",
                        "site_admin": False
                    },
                    "repo": repository
                },
                "author_association": "CONTRIBUTOR",
                "auto_merge": None,
                "active_lock_reason": None,
                "merged": True,
                "mergeable": None,
                "rebaseable": None,
                "mergeable_state": "unknown",
                "merged_by": {
                    "login": fake.user_name(),
                    "id": random.randint(1, 1000000),
                    "node_id": fake.uuid4(),
                    "gravatar_id": "",
                    "url": fake.url(),
                    "type": "User",
                    "site_admin": False
                },
                "comments": random.randint(0, 100),
                "review_comments": random.randint(0, 100),
                "maintainer_can_modify": False,
                "commits": random.randint(1, 100),
                "additions": random.randint(1, 10000),
                "deletions": random.randint(0, 10000),
                "changed_files": random.randint(1, 100)
            },
            "repository": repository,
            "organization": {
                "login": repository['owner']['login'],
                "id": repository['owner']['id'],
                "node_id": repository['owner']['node_id'],
                "url": repository['owner']['url'],
                "description": None
            },
            "sender": {
                "login": fake.user_name(),
                "id": random.randint(1, 1000000),
                "node_id": fake.uuid4(),
                "gravatar_id": "",
                "url": fake.url(),
                "type": "User",
                "site_admin": False
            }
        }
        response = requests.post(self.api_url_pull_request, json=body)
        if response.status_code == 200:
            print("Evento pull request cerrado enviado correctamente.")
        else:
            print(f"Error al enviar evento pull request cerrado: {response.status_code}")

    def _generate_commit_event(self):
        push_date = datetime.datetime.now(tz=datetime.timezone.utc)
        repository = self.repositories[random.randint(0, len(self.repositories) - 1)]
        commits = self._generate_commits(push_date)
        body = {
            "ref": "refs/heads/develop",
            "before": fake.sha1(),
            "after": fake.sha1(),
            "repository": repository,
            "pusher": {
                "name": fake.user_name(),
                "email": fake.email()
            },
            "organization": {
                "login": repository['owner']['login'],
                "id": repository['owner']['id'],
                "node_id": repository['owner']['node_id'],
                "url": repository['owner']['url'],
                "description": None
            },
            "sender": {
                "login": fake.user_name(),
                "id": random.randint(1, 1000000),
                "node_id": fake.uuid4(),
                "gravatar_id": "",
                "url": fake.url(),
                "type": "User"
            },
            "base_ref": None,
            "compare": fake.url(),
            "commits": commits,
            "head_commit": commits[0]
        }
        response = requests.post(self.api_url_commit, json=body)
        if response.status_code == 200:
            print("Evento push commits enviado correctamente.")
        else:
            print(f"Error al enviar evento push commits: {response.status_code}")
    
    def _generate_workflow_run_event(self):
        conclusion = random.choice(["success", "failure"])
        repository = self.repositories[random.randint(0, len(self.repositories) - 1)]
        head_commit = self._generate_commit(datetime.datetime.now(tz=datetime.timezone.utc))

        body = {
            "action": "completed",
            "workflow_run": {
                "id": random.randint(1, 1000000),
                "name": "CI",
                "node_id": fake.uuid4(),
                "head_branch": "main",
                "head_sha": head_commit["id"],
                "run_number": random.randint(1, 100),
                "event": "push",
                "status": "completed",
                "conclusion": conclusion,
                "workflow_id": random.randint(1, 100000),
                "check_suite_id": random.randint(1, 1000000),
                "check_suite_node_id": fake.uuid4(),
                "url": fake.url(),
                "pull_requests": [],
                "created_at": head_commit["timestamp"],
                "updated_at": head_commit["timestamp"],
                "run_attempt": 1,
                "run_started_at": head_commit["timestamp"],
                "repository": repository,
                "head_commit": head_commit
            }
        }
        response = requests.post(self.api_url_pipeline, json=body)
        if response.status_code == 200:
            print("Evento pipeline ejecutado enviado correctamente.")
        else:
            print(f"Error al enviar evento pipeline ejecutado: {response.status_code}")

    def _generate_repositories(self):
        repositories = []
        num_repos = 15
        for _ in range(1, num_repos):
            repositories.append(
                {
                    "id": random.choice(self.repositories_ids),
                    "node_id": fake.uuid4(),
                    "name": f"GH-{fake.word()}-{fake.word()}".upper(),
                    "full_name": f"{fake.user_name()}/{fake.word()}",
                    "private": True,
                    "owner": {
                        "login": fake.user_name(),
                        "id": random.randint(1, 1000000),
                        "node_id": fake.uuid4(),
                        "url": fake.url(),
                        "type": "Organization",
                        "site_admin": False
                    },
                    "url": fake.url(),
                    "created_at": fake.date_time_this_decade().isoformat(),
                    "updated_at": fake.date_time_this_decade().isoformat(),
                    "pushed_at": fake.date_time_this_decade().isoformat(),
                    "visibility": "private",
                    "watchers": random.randint(0, 100),
                    "default_branch": "main",
                    "master_branch": "main",
                    "organization": fake.company(),
                    "custom_properties": {}
                }
            )
        return repositories

    def _generate_commits(self, end_date: datetime.datetime) -> list[dict]:
        commits = []
        commit_number = random.randint(1, 8)
        for _ in range(0, commit_number):
            commit_date = end_date - datetime.timedelta(
                minutes=random.randint(0, 60 * 12), days=random.randint(0, 3)
            )
            commits.append(
                {
                    "id": fake.sha1(),
                    "tree_id": fake.sha1(),
                    "distinct": True,
                    "message": fake.sentence(),
                    "timestamp": commit_date.isoformat(),
                    "url": fake.url(),
                    "author": {
                        "name": fake.name(),
                        "email": fake.email(),
                        "username": fake.user_name()
                    },
                    "committer": {
                        "name": fake.name(),
                        "email": fake.email(),
                        "username": fake.user_name()
                    },
                    "added": [fake.file_path() for _ in range(random.randint(1, 5))],
                    "removed": [fake.file_path() for _ in range(random.randint(0, 2))],
                    "modified": [fake.file_path() for _ in range(random.randint(0, 3))]
                }
            )
        return commits

    def _generate_commit(self, end_date: datetime.datetime) -> dict:
        commit_date = end_date - datetime.timedelta(
            minutes=random.randint(0, 60 * 12), days=random.randint(0, 3)
        )
        return {
            "id": fake.sha1(),
            "tree_id": fake.sha1(),
            "distinct": True,
            "message": fake.sentence(),
            "timestamp": commit_date.isoformat(),
            "url": fake.url(),
            "author": {
                "name": fake.name(),
                "email": fake.email(),
                "username": fake.user_name()
            },
            "committer": {
                "name": fake.name(),
                "email": fake.email(),
                "username": fake.user_name()
            },
            "added": [fake.file_path() for _ in range(random.randint(1, 5))],
            "removed": [fake.file_path() for _ in range(random.randint(0, 2))],
            "modified": [fake.file_path() for _ in range(random.randint(0, 3))]
        }
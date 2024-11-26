from abc import abstractmethod, ABC
from config import Config

class Generator(ABC):
    """
    Abstract class for generating events for a provider
    """
    def __init__(self, config: Config):
        self.config = config

    @abstractmethod
    def generate(self, choices: list[str], weights: list) -> None:
        pass
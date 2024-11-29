package co.com.challenge.model.repositorymodel.gateways;

import co.com.challenge.model.repositorymodel.RepositoryModel;
import reactor.core.publisher.Mono;

public interface RepositoryModelRepository {
    Mono<String> storeRepository(RepositoryModel repositoryModel);
}

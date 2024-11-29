package co.com.challenge.usecase.pullrequest;

import co.com.challenge.model.pullrequestmodel.PullRequestModel;
import co.com.challenge.model.pullrequestmodel.gateways.PullRequestModelRepository;
import co.com.challenge.model.repositorymodel.RepositoryModel;
import co.com.challenge.model.repositorymodel.gateways.RepositoryModelRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PullRequestUseCase {
    private final PullRequestModelRepository pullRequestModelRepository;
    private final RepositoryModelRepository repositoryModelRepository;

    public Mono<Void> storePullRequestData(RepositoryModel repository, PullRequestModel pullRequestModel) {
        return repositoryModelRepository.storeRepository(repository)
                .flatMap(repositoryId -> pullRequestModelRepository.storePullRequestData(pullRequestModel))
                .then();
    }
}

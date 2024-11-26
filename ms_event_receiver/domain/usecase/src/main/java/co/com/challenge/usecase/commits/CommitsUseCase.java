package co.com.challenge.usecase.commits;

import co.com.challenge.model.commitmodel.CommitModel;
import co.com.challenge.model.commitmodel.gateways.CommitModelRepository;
import co.com.challenge.model.repositorymodel.RepositoryModel;
import co.com.challenge.model.repositorymodel.gateways.RepositoryModelRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class CommitsUseCase {
    private final CommitModelRepository commitModelRepository;
    private final RepositoryModelRepository repositoryModelRepository;

    public Mono<Void> storeCommitsData(RepositoryModel repository, Flux<CommitModel> commitList){
        return repositoryModelRepository.storeRepository(repository)
                .flatMap(repositoryId -> commitModelRepository.
                        storeCommitsData(buildCommitList(repositoryId,commitList)));
    }

    private Flux<CommitModel> buildCommitList(String repositoryId, Flux<CommitModel> commitList) {
        return commitList.map(commit -> {
            commit.setRepositoryId(repositoryId);
            return commit;
        });
    }




}

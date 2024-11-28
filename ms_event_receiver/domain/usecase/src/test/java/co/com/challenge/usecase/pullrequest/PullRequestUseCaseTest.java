package co.com.challenge.usecase.pullrequest;

import co.com.challenge.model.pullrequestmodel.PullRequestModel;
import co.com.challenge.model.pullrequestmodel.gateways.PullRequestModelRepository;
import co.com.challenge.model.repositorymodel.gateways.RepositoryModelRepository;
import co.com.challenge.usecase.providers.RepositoryProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PullRequestUseCaseTest {
    @Mock
    private  PullRequestModelRepository pullRequestModelRepository;
    @Mock
    private  RepositoryModelRepository repositoryModelRepository;

    @InjectMocks
    private PullRequestUseCase pullRequestUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pullRequestUseCase = new PullRequestUseCase(pullRequestModelRepository,repositoryModelRepository);
    }

    @Test
    void storeCommitAndRepo(){
        when(repositoryModelRepository.storeRepository(any())).thenReturn(Mono.just("123456789"));
        when(pullRequestModelRepository.storePullRequestData(any())).thenReturn(Mono.empty());

        StepVerifier.create(pullRequestUseCase.storePullRequestData(RepositoryProvider.buildRepositoryModel(),
                        PullRequestModel.builder().build()))
                .expectNextCount(0)
                .expectComplete()
                .verify();

        verify(repositoryModelRepository).storeRepository(RepositoryProvider.buildRepositoryModel());
    }
}

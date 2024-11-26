package co.com.challenge.usecase.commits;

import co.com.challenge.model.commitmodel.gateways.CommitModelRepository;
import co.com.challenge.model.repositorymodel.RepositoryModel;
import co.com.challenge.model.repositorymodel.gateways.RepositoryModelRepository;
import co.com.challenge.usecase.providers.CommitProvider;
import co.com.challenge.usecase.providers.RepositoryProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommitUseCaseTest {
    @Mock
    private CommitModelRepository commitModelRepository;
    @Mock
    private RepositoryModelRepository repositoryModelRepository;
    @InjectMocks
    private CommitUseCase commitUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commitUseCase = new CommitUseCase(commitModelRepository,repositoryModelRepository);
    }

    @Test
    void storeCommitAndRepo(){
        when(repositoryModelRepository.storeRepository(any())).thenReturn(Mono.just("123456789"));
        when(commitModelRepository.storeCommitsData(any())).thenReturn(Mono.empty());
        StepVerifier.create(commitUseCase.storeCommitsData(RepositoryProvider.buildRepositoryModel(),
                        Flux.just(CommitProvider.builCommitModel())))
                .expectNextCount(0)
                .expectComplete()
                .verify();
        verify(repositoryModelRepository).storeRepository(RepositoryProvider.buildRepositoryModel());
    }

}

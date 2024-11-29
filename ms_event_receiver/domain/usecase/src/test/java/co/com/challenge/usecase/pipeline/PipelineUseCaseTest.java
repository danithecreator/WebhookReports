package co.com.challenge.usecase.pipeline;

import co.com.challenge.model.pipelinemodel.PipelineModel;
import co.com.challenge.model.pipelinemodel.gateways.PipelineModelRepository;
import co.com.challenge.model.repositorymodel.gateways.RepositoryModelRepository;
import co.com.challenge.usecase.providers.RepositoryProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PipelineUseCaseTest {
    @Mock
    private PipelineModelRepository pipelineModelRepository;
    @Mock
    private RepositoryModelRepository repositoryModelRepository;

    @InjectMocks
    private PipelineUseCase pipelineUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pipelineUseCase = new PipelineUseCase(pipelineModelRepository,repositoryModelRepository);
    }

    @Test
    void storeCommitAndRepo(){
        when(repositoryModelRepository.storeRepository(any())).thenReturn(Mono.just("123456789"));
        when(pipelineModelRepository.storePipelineData(any())).thenReturn(Mono.empty());

        StepVerifier.create(pipelineUseCase.storePipelineData(RepositoryProvider.buildRepositoryModel(),
                        PipelineModel.builder().build()))
                .expectNextCount(0)
                .expectComplete()
                .verify();

        verify(repositoryModelRepository).storeRepository(RepositoryProvider.buildRepositoryModel());
    }
}

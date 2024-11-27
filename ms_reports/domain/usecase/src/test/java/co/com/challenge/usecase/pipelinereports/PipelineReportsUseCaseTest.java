package co.com.challenge.usecase.pipelinereports;

import co.com.challenge.model.pipeline.gateways.PipelineModelRepository;
import co.com.challenge.usecase.commitreports.CommitReportsUseCase;
import co.com.challenge.usecase.providers.CommitProvider;
import co.com.challenge.usecase.providers.PipelineProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PipelineReportsUseCaseTest {
    @Mock
    private PipelineModelRepository pipelineModelRepository;
    @InjectMocks
    private PipelineReportsUseCase pipelineReportsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pipelineReportsUseCase = new PipelineReportsUseCase(pipelineModelRepository);
    }

    @Test
    void getTotalPipelineRunsTest(){
        when(pipelineModelRepository.getTotalPipelineRuns(any())).thenReturn(Mono.just(PipelineProvider
                .buildPipelineModelResponse()));

        StepVerifier.create(pipelineReportsUseCase.getTotalPipelineRuns(PipelineProvider.buildPipelineModelRequest()))
                .assertNext(resp ->{
                    assertEquals(5,resp.getTotal());
                    assertEquals(3, resp.getSuccessful());
                })
                .verifyComplete();

    }

}

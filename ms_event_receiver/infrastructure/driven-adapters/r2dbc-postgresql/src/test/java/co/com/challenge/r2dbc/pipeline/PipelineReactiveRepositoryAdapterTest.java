package co.com.challenge.r2dbc.pipeline;

import co.com.challenge.model.pipelinemodel.PipelineModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PipelineReactiveRepositoryAdapterTest {
    @Mock
    private PipelineReactiveRepository mockRepository;

    @Mock
    private ObjectMapper mockMapper;

    private PipelineReactiveRepositoryAdapter repositoryAdapter;

    @BeforeEach
    void setUp() {
        repositoryAdapter = new PipelineReactiveRepositoryAdapter(mockRepository, mockMapper);
    }

    @Test
    void storeCommitsDataSuccess() {
        PipelineModel pipelineModel = PipelineModel.builder().build();
        when(mockRepository.save(any())).thenReturn(Mono.empty());
        StepVerifier.create(repositoryAdapter.storePipelineData(pipelineModel))
                .expectComplete()
                .verify();
    }
}

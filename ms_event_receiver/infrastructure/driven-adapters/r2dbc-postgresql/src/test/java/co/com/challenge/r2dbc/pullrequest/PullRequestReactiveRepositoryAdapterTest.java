package co.com.challenge.r2dbc.pullrequest;


import co.com.challenge.model.pullrequestmodel.PullRequestModel;
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
class PullRequestReactiveRepositoryAdapterTest {
    @Mock
    private PullRequestReactiveRepository mockRepository;

    @Mock
    private ObjectMapper mockMapper;

    private PullRequestReactiveRepositoryAdapter repositoryAdapter;

    @BeforeEach
    void setUp() {
        repositoryAdapter = new PullRequestReactiveRepositoryAdapter(mockRepository, mockMapper);
    }

    @Test
    void storeCommitsDataSuccess() {
        PullRequestModel pipelineModel = PullRequestModel.builder().build();
        when(mockRepository.save(any())).thenReturn(Mono.empty());
        StepVerifier.create(repositoryAdapter.storePullRequestData(pipelineModel))
                .expectComplete()
                .verify();
    }
}

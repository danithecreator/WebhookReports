package co.com.challenge.usecase.pullrequestreports;

import co.com.challenge.model.pullrequest.gateways.PullRequestModelRepository;
import co.com.challenge.usecase.commitreports.CommitReportsUseCase;
import co.com.challenge.usecase.providers.PipelineProvider;
import co.com.challenge.usecase.providers.PullRequestProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PullRequestReportsUseCaseTest {
    @Mock
    private PullRequestModelRepository pullRequestModelRepository;
    @InjectMocks
    private PullRequestReportsUseCase pullRequestReportsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pullRequestReportsUseCase = new PullRequestReportsUseCase(pullRequestModelRepository);
    }

    @Test
    void getTotalClosedPullRequestByDateRange(){
        when(pullRequestModelRepository.getTotalClosedPullRequestByDateRange(any()))
                .thenReturn(Mono.just(PullRequestProvider.buildPullRequestModelResponse()));

        StepVerifier.create(pullRequestReportsUseCase.getTotalClosedPullRequestByDateRange(PullRequestProvider
                        .buildPullRequestModelRequest()))
                .assertNext(resp ->{
                    assertEquals(5,resp.getTotal());
                })
                .verifyComplete();
    }

}

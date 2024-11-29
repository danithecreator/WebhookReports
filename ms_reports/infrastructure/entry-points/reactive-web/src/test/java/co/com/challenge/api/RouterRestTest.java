package co.com.challenge.api;

import co.com.challenge.api.helper.response.commit.CommitResponseBuilder;
import co.com.challenge.api.provider.CommitterProvider;
import co.com.challenge.api.provider.PipelineProvider;
import co.com.challenge.api.provider.PullRequestProvider;
import co.com.challenge.model.commit.CommitModelResponse;
import co.com.challenge.model.constants.Constants;
import co.com.challenge.usecase.commitreports.CommitReportsUseCase;
import co.com.challenge.usecase.pipelinereports.PipelineReportsUseCase;
import co.com.challenge.usecase.pullrequestreports.PullRequestReportsUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ContextConfiguration(classes = {RouterRest.class, Handler.class})
@WebFluxTest
class RouterRestTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CommitReportsUseCase commitReportsUseCase;
    @MockBean
    private PullRequestReportsUseCase pullRequestReportsUseCase;
    @MockBean
    private PipelineReportsUseCase pipelineReportsUseCase;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @DisplayName("should return top committers")
    @Test
    void test200ThirdPartyGetData() {
        given(commitReportsUseCase.getTopCommittersByDateRange(any())).willReturn(Flux.just(CommitterProvider
                .buildCommitModelResponse(), CommitterProvider
                .buildCommitModelResponse()));


        webTestClient.post()
                .uri(Constants.BASE_PATH + Constants.OPERATION_GET_TOP_COMMITTERS)
                .accept(APPLICATION_JSON)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .bodyValue(CommitterProvider.buildRequestCommit())
                .exchange()
                .expectStatus().isOk();
    }

    @DisplayName("should return total pipelines runs")
    @Test
    void listenPOSTTotalPipelinesRunsTest() {
        given(pipelineReportsUseCase.getTotalPipelineRuns(any())).willReturn(Mono.just(PipelineProvider
                .buildPipelineModelResponse()));

        webTestClient.post()
                .uri(Constants.BASE_PATH + Constants.OPERATION_GET_TOTAL_PIPELINES_RUNS)
                .accept(APPLICATION_JSON)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .bodyValue(PipelineProvider.buildPipelineModelRequest())
                .exchange()
                .expectStatus().isOk();
    }

    @DisplayName("should return total pullrequest closed")
    @Test
    void listenPOSTTotalClosedPrTest() {
        given(pullRequestReportsUseCase.getTotalClosedPullRequestByDateRange(any())).willReturn(Mono.just(PullRequestProvider
                .buildPullRequestModelResponse()));

        webTestClient.post()
                .uri(Constants.BASE_PATH + Constants.OPERATION_GET_TOTAL_CLOSED_PR)
                .accept(APPLICATION_JSON)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .bodyValue(PullRequestProvider.buildPullRequestModelRequest())
                .exchange()
                .expectStatus().isOk();
    }
}

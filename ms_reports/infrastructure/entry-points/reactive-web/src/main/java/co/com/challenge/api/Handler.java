package co.com.challenge.api;

import co.com.challenge.api.dtos.request.CommitReportRequest;
import co.com.challenge.api.dtos.request.PipelineReportRequest;
import co.com.challenge.api.dtos.request.PullRequestReportRequest;
import co.com.challenge.api.helper.request.ModelBuilder;
import co.com.challenge.api.helper.response.commit.CommitResponseBuilder;
import co.com.challenge.api.helper.response.pipeline.PipelineResponseBuilder;
import co.com.challenge.api.helper.response.pullrequest.PullRequestResponseBuilder;
import co.com.challenge.usecase.commitreports.CommitReportsUseCase;
import co.com.challenge.usecase.pipelinereports.PipelineReportsUseCase;
import co.com.challenge.usecase.pullrequestreports.PullRequestReportsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final CommitReportsUseCase commitReportsUseCase;
    private final PullRequestReportsUseCase pullRequestReportsUseCase;
    private final PipelineReportsUseCase pipelineReportsUseCase;

    public Mono<ServerResponse> listenPOSTTopCommitters(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(CommitReportRequest.class)
                .flatMap(request ->{
                    var requestModel = ModelBuilder.buildCommitModel(request);
                    return commitReportsUseCase.getTopCommittersByDateRange(requestModel)
                            .doOnNext(System.out::println)
                            .collectList()
                            .flatMap(CommitResponseBuilder::buildTopCommittersResponse);
                });
    }


    public Mono<ServerResponse> listenPOSTTotalCommits(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(CommitReportRequest.class)
                .flatMap(request ->{
                    var requestModel = ModelBuilder.buildCommitModel(request);
                    return commitReportsUseCase.getTotalCommitsByDateRange(requestModel)
                            .flatMap(CommitResponseBuilder::buildTotalCommitsResponse);
                });
    }

    public Mono<ServerResponse> listenPOSTTotalCommitsByCommitter(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(CommitReportRequest.class)
                .flatMap(request ->{
                    var requestModel = ModelBuilder.buildCommitModel(request);
                    return commitReportsUseCase.getTotalCommitsByCommittersNameInDateRange(requestModel)
                            .flatMap(CommitResponseBuilder::buildTotalCommitsByCommitterResponse);
                });
    }

    public Mono<ServerResponse> listenPOSTTotalClosedPr(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(PullRequestReportRequest.class)
                .flatMap(request ->{
                    var requestModel = ModelBuilder.buildPullrequestModel(request);
                    return pullRequestReportsUseCase.getTotalClosedPullRequestByDateRange(requestModel)
                            .flatMap(PullRequestResponseBuilder::buildTotalPullRequestResponse);
                });
    }

    public Mono<ServerResponse> listenPOSTTotalPipelinesRuns(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(PipelineReportRequest.class)
                .flatMap(request ->{
                    var requestModel = ModelBuilder.buildPipelineRequest(request);
                    return pipelineReportsUseCase.getTotalPipelineRuns(requestModel)
                            .flatMap(PipelineResponseBuilder::buildTotalPipelinesRunsResponse);
                });
    }

}
package co.com.challenge.api;

import co.com.challenge.api.dtos.request.CommitReportRequest;
import co.com.challenge.api.helper.ModelBuilder;
import co.com.challenge.usecase.commitreports.CommitReportsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final CommitReportsUseCase commitReportsUseCase;

    public Mono<ServerResponse> listenPOSTTopCommitters(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(CommitReportRequest.class)
                .flatMap(request ->{
                    var requestModel = ModelBuilder.buildCommitModel(request);
                    return commitReportsUseCase.getTopCommittersByDateRange(requestModel)
                            .collectList()
                            .flatMap(response -> ServerResponse.ok().bodyValue(response));
                });
    }


    public Mono<ServerResponse> listenPOSTTotalCommits(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(CommitReportRequest.class)
                .flatMap(request ->{
                    var requestModel = ModelBuilder.buildCommitModel(request);
                    return commitReportsUseCase.getTotalCommitsByDateRange(requestModel)
                            .flatMap(response -> ServerResponse.ok().bodyValue(response));
                });
    }


}
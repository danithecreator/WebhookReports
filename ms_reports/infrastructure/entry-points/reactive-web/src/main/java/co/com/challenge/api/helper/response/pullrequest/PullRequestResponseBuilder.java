package co.com.challenge.api.helper.response.pullrequest;
import co.com.challenge.api.dtos.response.pullrequest.TotalPullRequestResponse;
import co.com.challenge.model.pullrequest.PullRequestModelResponse;
import lombok.experimental.UtilityClass;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@UtilityClass

public class PullRequestResponseBuilder {
    public Mono<ServerResponse> buildTotalPullRequestResponse(PullRequestModelResponse pullRequestModelResponse){
        return ServerResponse
                .ok()
                .bodyValue(TotalPullRequestResponse.builder()
                        .totalPullRequestCompleted(pullRequestModelResponse.getTotal())
                        .build());
    }
}

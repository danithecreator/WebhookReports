package co.com.challenge.model.pullrequest.gateways;

import co.com.challenge.model.pullrequest.PullRequestModelRequest;
import co.com.challenge.model.pullrequest.PullRequestModelResponse;
import reactor.core.publisher.Mono;

public interface PullRequestModelRepository {
    Mono<PullRequestModelResponse> getTotalClosedPullRequestByDateRange(PullRequestModelRequest pullRequestModelRequest);
}

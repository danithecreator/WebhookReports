package co.com.challenge.usecase.pullrequestreports;

import co.com.challenge.model.pullrequest.PullRequestModelRequest;
import co.com.challenge.model.pullrequest.PullRequestModelResponse;
import co.com.challenge.model.pullrequest.gateways.PullRequestModelRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PullRequestReportsUseCase {
    private final PullRequestModelRepository pullRequestModelRepository;

    public Mono<PullRequestModelResponse> getTotalClosedPullRequestByDateRange(PullRequestModelRequest pullRequestModelRequest){
        return pullRequestModelRepository.getTotalClosedPullRequestByDateRange(pullRequestModelRequest);
    }
}

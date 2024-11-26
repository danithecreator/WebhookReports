package co.com.challenge.model.pullrequestmodel.gateways;


import co.com.challenge.model.pullrequestmodel.PullRequestModel;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface PullRequestModelRepository {
    Mono<Void> storePullRequestData(PullRequestModel pullRequestModel);
}

package co.com.challenge.model.commit.gateways;

import co.com.challenge.model.commit.CommitModelRequest;
import co.com.challenge.model.commit.CommitModelResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface CommitModelRepository {
    Mono<Long> getTotalCommitsByDateRange(CommitModelRequest commitModelRequest);
    Flux<CommitModelResponse> getTopCommittersByDateRange(CommitModelRequest commitModelRequest);
    Mono<CommitModelRequest> getTotalCommitsByCommittersNameInDateRange(CommitModelRequest commitModelRequest);
}

package co.com.challenge.usecase.commitreports;

import co.com.challenge.model.commit.CommitModelRequest;
import co.com.challenge.model.commit.CommitModelResponse;
import co.com.challenge.model.commit.gateways.CommitModelRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CommitReportsUseCase {
    private final CommitModelRepository commitModelRepository;

    public Mono<Long> getTotalCommitsByDateRange(CommitModelRequest commitModelRequest){
        return commitModelRepository.getTotalCommitsByDateRange(commitModelRequest);
    }

    public Flux<CommitModelResponse> getTopCommittersByDateRange(CommitModelRequest commitModelRequest){
        return commitModelRepository.getTopCommittersByDateRange(commitModelRequest);
    }
    public Mono<CommitModelResponse> getTotalCommitsByCommittersNameInDateRange(CommitModelRequest commitModelRequest){
        return commitModelRepository.getTotalCommitsByCommittersNameInDateRange(commitModelRequest);
    }
}

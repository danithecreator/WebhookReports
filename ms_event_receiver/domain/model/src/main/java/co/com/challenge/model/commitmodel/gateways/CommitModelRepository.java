package co.com.challenge.model.commitmodel.gateways;

import co.com.challenge.model.commitmodel.CommitModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;


public interface CommitModelRepository {
    Mono<Void> storeCommitsData(Flux<CommitModel> commitList);
}


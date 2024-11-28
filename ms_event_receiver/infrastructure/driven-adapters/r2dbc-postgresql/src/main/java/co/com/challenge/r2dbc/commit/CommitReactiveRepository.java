package co.com.challenge.r2dbc.commit;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


public interface CommitReactiveRepository extends ReactiveCrudRepository<CommitDto, Long>,
        ReactiveQueryByExampleExecutor<CommitDto> {
}

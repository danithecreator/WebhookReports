package co.com.challenge.r2dbc.pullrequest;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;


public interface PullRequestReactiveRepository extends ReactiveCrudRepository<PullRequestDto, Long>,
        ReactiveQueryByExampleExecutor<PullRequestDto> {


}

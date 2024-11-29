package co.com.challenge.r2dbc.pullrequest;


import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PullRequestReactiveRepository extends ReactiveCrudRepository<PullRequestDto, Long>,
        ReactiveQueryByExampleExecutor<PullRequestDto> {


}

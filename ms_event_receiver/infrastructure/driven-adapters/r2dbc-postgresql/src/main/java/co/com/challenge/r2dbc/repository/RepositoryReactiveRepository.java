package co.com.challenge.r2dbc.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;


public interface RepositoryReactiveRepository extends ReactiveCrudRepository<RepositoryDto, String>,
        ReactiveQueryByExampleExecutor<RepositoryDto> {

    @Query(value ="SELECT repository_id from \"public\".repository where repository_id = :id")
    Mono<String> findRepositoryByRepositoryId(@Param("id") String id);

}

package co.com.challenge.r2dbc.pipeline;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface PipelineReactiveRepository extends ReactiveCrudRepository<PipelineDto, Long>,
        ReactiveQueryByExampleExecutor<PipelineDto> {

}

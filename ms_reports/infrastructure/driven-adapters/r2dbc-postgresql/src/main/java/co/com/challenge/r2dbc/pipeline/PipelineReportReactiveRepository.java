package co.com.challenge.r2dbc.pipeline;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;


public interface PipelineReportReactiveRepository extends ReactiveCrudRepository<PipelineReportDto, Long>,
        ReactiveQueryByExampleExecutor<PipelineReportDto> {
    @Query("SELECT " +
            "COUNT(*) AS total, " +
            "COUNT(CASE WHEN pipeline_status = 'succeeded' THEN 1 END) AS successful, " +
            "COUNT(CASE WHEN pipeline_status = 'failed' THEN 1 END) AS failed " +
            "FROM public.pipeline " +
            "WHERE pipeline_date BETWEEN :initialDate AND :finishDate")
    Mono<PipelineReportDto> getTotalPipelineRuns(@Param("initialDate") OffsetDateTime initialDate,
                                                 @Param("finishDate") OffsetDateTime finishDate);

}

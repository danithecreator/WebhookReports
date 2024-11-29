package co.com.challenge.r2dbc.pullrequestreport;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;


public interface PullRequestReportReactiveRepository extends ReactiveCrudRepository<PullRequestReportDto, Long>,
        ReactiveQueryByExampleExecutor<PullRequestReportDto> {
    @Query("SELECT COUNT(*) AS total " +
            "FROM public.pullrequest " +
            "WHERE pullrequest_closed_at BETWEEN :initialDate AND :finishDate")
    Mono<PullRequestReportDto> getTotalPRClosedByDate(@Param("initialDate") OffsetDateTime initialDate,
                                                      @Param("finishDate") OffsetDateTime finishDate);

}

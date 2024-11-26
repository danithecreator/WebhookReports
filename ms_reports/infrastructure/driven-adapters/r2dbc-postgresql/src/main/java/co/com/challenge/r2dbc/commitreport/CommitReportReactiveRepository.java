package co.com.challenge.r2dbc.commitreport;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


public interface CommitReportReactiveRepository extends ReactiveCrudRepository<CommitReportDto, Long>,
        ReactiveQueryByExampleExecutor<CommitReportDto> {

    @Query("SELECT COUNT(*) AS total " +
            "FROM public.commit " +
            "WHERE commit_date BETWEEN TO_TIMESTAMP(:initialDate || ' 00:00:00', 'DD/MM/YYYY HH24:MI:SS') \n" +
            "             AND TO_TIMESTAMP(:finishDate || ' 23:59:59', 'DD/MM/YYYY HH24:MI:SS')"
    )
    Mono<Long> totalCommitsByDateRange(@Param("initialDate") String initialDate,
                                       @Param("finishDate") String finishDate);

    @Query("SELECT commit_committer AS committer, COUNT(*) AS total " +
            "FROM public.commit " +
            "WHERE commit_date BETWEEN TO_TIMESTAMP(:initialDate || ' 00:00:00', 'DD/MM/YYYY HH24:MI:SS') \n" +
            "             AND TO_TIMESTAMP(:finishDate || ' 23:59:59', 'DD/MM/YYYY HH24:MI:SS')\n" +
            "GROUP BY commit_committer " +
            "ORDER BY total DESC " +
            "LIMIT :top")
    Flux<CommitReportDto> topCommittersByDateRange(
            @Param("initialDate") String initialDate,
            @Param("finishDate") String finishDate,
            @Param("top") Integer top);

}

package co.com.challenge.r2dbc.commitreport;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;


public interface CommitReportReactiveRepository extends ReactiveCrudRepository<CommitReportDto, Long>,
        ReactiveQueryByExampleExecutor<CommitReportDto> {

    @Query("SELECT COUNT(*) AS total " +
            "FROM public.commit " +
            "WHERE commit_date BETWEEN :initialDate AND :finishDate")
    Mono<Long> totalCommitsByDateRange(@Param("initialDate") OffsetDateTime initialDate,
                                       @Param("finishDate") OffsetDateTime finishDate);
    @Query("SELECT commit_committer AS committer, COUNT(*) AS total " +
            "FROM public.commit " +
            "WHERE commit_date BETWEEN :initialDate AND :finishDate " +
            "GROUP BY commit_committer " +
            "ORDER BY total DESC " +
            "LIMIT :top")
    Flux<CommitReportDto> topCommittersByDateRange(
            @Param("initialDate") OffsetDateTime initialDate,
            @Param("finishDate") OffsetDateTime finishDate,
            @Param("top") Integer top);

    @Query("SELECT commit_committer AS committer, COUNT(*) AS total " +
            "FROM public.commit " +
            "WHERE commit_date BETWEEN :initialDate AND :finishDate " +
            "AND commit_committer = :committer " +
            "GROUP BY commit_committer")
    Mono<CommitReportDto> totalCommitsByCommitterName(@Param("initialDate") OffsetDateTime initialDate,
                                                      @Param("finishDate") OffsetDateTime finishDate,
                                                      @Param("committer") String committer);

}

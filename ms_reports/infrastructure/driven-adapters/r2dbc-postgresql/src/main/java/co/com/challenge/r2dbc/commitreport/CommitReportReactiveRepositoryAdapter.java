package co.com.challenge.r2dbc.commitreport;


import co.com.challenge.model.commit.CommitModelRequest;
import co.com.challenge.model.commit.CommitModelResponse;
import co.com.challenge.model.commit.gateways.CommitModelRepository;
import co.com.challenge.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Repository
public class CommitReportReactiveRepositoryAdapter
        extends ReactiveAdapterOperations<CommitModelResponse, CommitReportDto, Long, CommitReportReactiveRepository>
        implements CommitModelRepository {

    private static final Logger LOG = LoggerFactory.getLogger(CommitReportReactiveRepositoryAdapter.class);

    public CommitReportReactiveRepositoryAdapter(CommitReportReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, CommitModelResponse.CommitModelResponseBuilder.class).build());
    }

    @Override
    public Mono<Long> getTotalCommitsByDateRange(CommitModelRequest commitModelRequest) {
        return repository.totalCommitsByDateRange(commitModelRequest.getInitialDate(),
                commitModelRequest.getFinishDate());
    }

    @Override
    public Flux<CommitModelResponse> getTopCommittersByDateRange(CommitModelRequest commitModelRequest) {
        return repository.topCommittersByDateRange(commitModelRequest.getInitialDate(),
                        commitModelRequest.getFinishDate(),commitModelRequest.getTop())
                .map(super::toEntity);
    }

    @Override
    public Mono<CommitModelResponse> getTotalCommitsByCommittersNameInDateRange(CommitModelRequest commitModelRequest) {
        return repository.totalCommitsByCommitterName(commitModelRequest.getInitialDate(),
                commitModelRequest.getFinishDate(),commitModelRequest.getCommittersName())
                .map(super::toEntity);
    }
}

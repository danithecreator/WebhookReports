package co.com.challenge.r2dbc.pullrequestreport;


import co.com.challenge.model.commit.CommitModelRequest;
import co.com.challenge.model.commit.CommitModelResponse;
import co.com.challenge.model.pullrequest.PullRequestModelRequest;
import co.com.challenge.model.pullrequest.PullRequestModelResponse;
import co.com.challenge.model.pullrequest.gateways.PullRequestModelRepository;
import co.com.challenge.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Repository
public class PullRequestReportReactiveRepositoryAdapter
        extends ReactiveAdapterOperations<PullRequestModelResponse, PullRequestReportDto, Long, PullRequestReportReactiveRepository>
        implements PullRequestModelRepository {

    private static final Logger LOG = LoggerFactory.getLogger(PullRequestReportReactiveRepositoryAdapter.class);

    public PullRequestReportReactiveRepositoryAdapter(PullRequestReportReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, PullRequestModelResponse.PullRequestModelResponseBuilder.class).build());
    }


    @Override
    public Mono<PullRequestModelResponse> getTotalClosedPullRequestByDateRange(PullRequestModelRequest pullRequestModelRequest) {
        return repository.getTotalPRClosedByDate(pullRequestModelRequest.getInitialDate(),
                pullRequestModelRequest.getFinishDate())
                .map(super::toEntity);
    }
}

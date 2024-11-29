package co.com.challenge.r2dbc.pullrequest;

import co.com.challenge.model.pullrequestmodel.PullRequestModel;
import co.com.challenge.model.pullrequestmodel.gateways.PullRequestModelRepository;
import co.com.challenge.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@Repository
public class PullRequestReactiveRepositoryAdapter extends ReactiveAdapterOperations<PullRequestModel,
        PullRequestDto, Long, PullRequestReactiveRepository>
        implements PullRequestModelRepository {

    private static final Logger LOG = LoggerFactory.getLogger(PullRequestReactiveRepositoryAdapter.class);

    public PullRequestReactiveRepositoryAdapter(PullRequestReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d,PullRequestModel.PullRequestModelBuilder.class).build());
    }


    @Override
    public Mono<Void> storePullRequestData(PullRequestModel pullRequestModel) {
        return super.save(pullRequestModel).then();
    }


}

package co.com.challenge.r2dbc.commit;

import co.com.challenge.model.commitmodel.CommitModel;
import co.com.challenge.model.commitmodel.gateways.CommitModelRepository;
import co.com.challenge.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CommitReactiveRepositoryAdapter extends ReactiveAdapterOperations<CommitModel, CommitDto, Long, CommitReactiveRepository>
        implements CommitModelRepository {

    private static final Logger LOG = LoggerFactory.getLogger(CommitReactiveRepositoryAdapter.class);

    public CommitReactiveRepositoryAdapter(CommitReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, CommitModel.CommitModelBuilder.class).build());
    }

    @Override
    public Mono<Void> storeCommitsData(Flux<CommitModel> commitList) {
        return super.saveAllEntities(commitList)
                .then();
    }

}

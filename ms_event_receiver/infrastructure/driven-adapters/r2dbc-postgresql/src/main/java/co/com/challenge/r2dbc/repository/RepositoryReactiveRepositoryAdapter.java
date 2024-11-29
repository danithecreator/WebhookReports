package co.com.challenge.r2dbc.repository;

import co.com.challenge.model.commitmodel.CommitModel;
import co.com.challenge.model.commitmodel.gateways.CommitModelRepository;
import co.com.challenge.model.repositorymodel.RepositoryModel;
import co.com.challenge.model.repositorymodel.gateways.RepositoryModelRepository;
import co.com.challenge.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RepositoryReactiveRepositoryAdapter
        extends ReactiveAdapterOperations<RepositoryModel, RepositoryDto, String,
        RepositoryReactiveRepository>
        implements RepositoryModelRepository {

    private static final Logger LOG = LoggerFactory.getLogger(RepositoryReactiveRepositoryAdapter.class);

    public RepositoryReactiveRepositoryAdapter(RepositoryReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, RepositoryModel.RepositoryModelBuilder.class).build());
    }


    @Override
    public Mono<String> storeRepository(RepositoryModel repositoryModel) {
        return repository.findRepositoryByRepositoryId(repositoryModel.getRepositoryId())
                .switchIfEmpty(super.save(repositoryModel).map(RepositoryModel::getRepositoryId));
    }
}

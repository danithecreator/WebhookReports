package co.com.challenge.r2dbc.pipeline;

import co.com.challenge.model.pipelinemodel.PipelineModel;
import co.com.challenge.model.pipelinemodel.gateways.PipelineModelRepository;
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
public class PipelineReactiveRepositoryAdapter extends ReactiveAdapterOperations<PipelineModel,
        PipelineDto, Long, PipelineReactiveRepository>
        implements PipelineModelRepository {

    private static final Logger LOG = LoggerFactory.getLogger(PipelineReactiveRepositoryAdapter.class);

    public PipelineReactiveRepositoryAdapter(PipelineReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d,PipelineModel.PipelineModelBuilder.class).build());
    }


    @Override
    public Mono<Void> storePipelineData(PipelineModel pipelineModel) {
        System.out.println(pipelineModel.getRepositoryId());
        return super.save(pipelineModel).then();
    }

    @Override
    public Mono<Long> getTotalPipelineSuccessfulRunsByDateRange(LocalDateTime startDate, LocalDateTime finishDate) {
        return null;
    }

    @Override
    public Mono<Long> getTotalPipelineFailedRunsByDateRange(LocalDateTime startDate, LocalDateTime finishDate) {
        return null;
    }
}

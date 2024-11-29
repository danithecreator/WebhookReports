package co.com.challenge.r2dbc.pipeline;


import co.com.challenge.model.pipeline.PipelineModelRequest;
import co.com.challenge.model.pipeline.PipelineModelResponse;
import co.com.challenge.model.pipeline.gateways.PipelineModelRepository;
import co.com.challenge.model.pullrequest.PullRequestModelRequest;
import co.com.challenge.model.pullrequest.PullRequestModelResponse;
import co.com.challenge.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public class PipelineReportReactiveRepositoryAdapter
        extends ReactiveAdapterOperations<PipelineModelResponse, PipelineReportDto, Long, PipelineReportReactiveRepository>
        implements PipelineModelRepository {

    private static final Logger LOG = LoggerFactory.getLogger(PipelineReportReactiveRepositoryAdapter.class);

    public PipelineReportReactiveRepositoryAdapter(PipelineReportReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, PipelineModelResponse.PipelineModelResponseBuilder.class).build());
    }


    @Override
    public Mono<PipelineModelResponse> getTotalPipelineRuns(PipelineModelRequest pipelineModelRequest) {
        return repository.getTotalPipelineRuns(pipelineModelRequest.getInitialDate(),
                pipelineModelRequest.getFinishDate())
                .map(super::toEntity);
    }
}

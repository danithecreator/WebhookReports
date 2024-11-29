package co.com.challenge.model.pipeline.gateways;

import co.com.challenge.model.pipeline.PipelineModelRequest;
import co.com.challenge.model.pipeline.PipelineModelResponse;
import reactor.core.publisher.Mono;

public interface PipelineModelRepository {
    Mono<PipelineModelResponse> getTotalPipelineRuns(PipelineModelRequest pipelineModelRequest);
}

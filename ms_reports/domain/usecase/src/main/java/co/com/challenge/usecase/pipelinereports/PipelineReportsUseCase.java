package co.com.challenge.usecase.pipelinereports;

import co.com.challenge.model.pipeline.PipelineModelRequest;
import co.com.challenge.model.pipeline.PipelineModelResponse;
import co.com.challenge.model.pipeline.gateways.PipelineModelRepository;
import lombok.RequiredArgsConstructor;

import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class PipelineReportsUseCase {
    private final PipelineModelRepository pipelineModelRepository;

    public Mono<PipelineModelResponse> getTotalPipelineRuns(PipelineModelRequest pipelineModelRequest){
        return pipelineModelRepository.getTotalPipelineRuns(pipelineModelRequest);
    }
}

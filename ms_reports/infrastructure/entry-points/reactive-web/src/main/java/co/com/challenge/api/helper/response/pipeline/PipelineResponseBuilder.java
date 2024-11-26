package co.com.challenge.api.helper.response.pipeline;


import co.com.challenge.api.dtos.response.pipeline.TotalPipelinesRunsResponse;
import co.com.challenge.model.pipeline.PipelineModelResponse;
import lombok.experimental.UtilityClass;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@UtilityClass
public class PipelineResponseBuilder {
    public Mono<ServerResponse> buildTotalPipelinesRunsResponse(PipelineModelResponse pipelineModelResponse){
        return ServerResponse
                .ok()
                .bodyValue(TotalPipelinesRunsResponse.builder()
                        .totalRuns(pipelineModelResponse.getTotal())
                        .totalRunsByStatus(TotalPipelinesRunsResponse.TotalRunsByStatus.builder()
                                .successfulRuns(pipelineModelResponse.getSuccessful())
                                .failedRuns(pipelineModelResponse.getFailed())
                                .build())
                        .build());
    }
}

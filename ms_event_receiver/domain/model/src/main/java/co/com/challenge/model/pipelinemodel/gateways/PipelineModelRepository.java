package co.com.challenge.model.pipelinemodel.gateways;

import co.com.challenge.model.pipelinemodel.PipelineModel;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface PipelineModelRepository {
    Mono<Void> storePipelineData(PipelineModel pipelineModel);
    Mono<Long> getTotalPipelineSuccessfulRunsByDateRange(LocalDateTime startDate, LocalDateTime finishDate);
    Mono<Long> getTotalPipelineFailedRunsByDateRange(LocalDateTime startDate, LocalDateTime finishDate);
}

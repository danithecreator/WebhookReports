package co.com.challenge.usecase.pipeline;

import co.com.challenge.model.pipelinemodel.PipelineModel;
import co.com.challenge.model.pipelinemodel.gateways.PipelineModelRepository;
import co.com.challenge.model.repositorymodel.RepositoryModel;
import co.com.challenge.model.repositorymodel.gateways.RepositoryModelRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class PipelineUseCase {
    private final PipelineModelRepository pipelineModelRepository;
    private final RepositoryModelRepository repositoryModelRepository;

    public Mono<Void> storePipelineData(RepositoryModel repository, PipelineModel pipelineModel){
        return repositoryModelRepository.storeRepository(repository)
                .flatMap(repositoryId ->pipelineModelRepository.storePipelineData(pipelineModel)).then();
    }
    public Mono<Long> getTotalPipelineSuccessfulRunsByDateRange(LocalDateTime startDate, LocalDateTime finishDate){
        return pipelineModelRepository.getTotalPipelineSuccessfulRunsByDateRange(startDate,finishDate);
    }
    public Mono<Long> getTotalPipelineFailedRunsByDateRange(LocalDateTime startDate, LocalDateTime finishDate){
        return pipelineModelRepository.getTotalPipelineFailedRunsByDateRange(startDate,finishDate);
    }
}

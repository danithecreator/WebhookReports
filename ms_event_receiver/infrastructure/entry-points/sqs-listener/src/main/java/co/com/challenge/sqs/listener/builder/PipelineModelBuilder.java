package co.com.challenge.sqs.listener.builder;

import co.com.challenge.model.pipelinemodel.PipelineModel;
import co.com.challenge.sqs.listener.dto.PipelineDTO;

public class PipelineModelBuilder {
    public static PipelineModel buildPipelineModel(PipelineDTO pipelineDTO){
        return PipelineModel.builder()
                .pipelineId(pipelineDTO.getBody().getId())
                .url(pipelineDTO.getBody().getUrl())
                .date(pipelineDTO.getBody().getDate())
                .conclusion(pipelineDTO.getBody().getConclusion())
                .repositoryId(pipelineDTO.getBody().getRepository().getId())
                .build();
    }
}

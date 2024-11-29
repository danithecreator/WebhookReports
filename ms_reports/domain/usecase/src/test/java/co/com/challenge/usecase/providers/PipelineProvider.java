package co.com.challenge.usecase.providers;

import co.com.challenge.model.pipeline.PipelineModelRequest;
import co.com.challenge.model.pipeline.PipelineModelResponse;
import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;

@UtilityClass
public class PipelineProvider {
    public PipelineModelRequest buildPipelineModelRequest(){
        return PipelineModelRequest.builder()
                .initialDate(OffsetDateTime.now())
                .finishDate(OffsetDateTime.now())
                .build();
    }

    public PipelineModelResponse buildPipelineModelResponse(){
        return PipelineModelResponse.builder()
                .total(5)
                .successful(3)
                .failed(2)
                .build();
    }

}

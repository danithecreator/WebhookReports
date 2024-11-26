package co.com.challenge.sqs.listener.dto;

import co.com.challenge.sqs.listener.helper.PipelineDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PipelineDTO {
    private String resource;
    @JsonDeserialize(using = PipelineDeserializer.class)
    private Body body;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder(toBuilder = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Body {
        private String id;
        private String url;
        private LocalDateTime date;
        private String conclusion;
        private RepositoryDTO repository;
    }
}

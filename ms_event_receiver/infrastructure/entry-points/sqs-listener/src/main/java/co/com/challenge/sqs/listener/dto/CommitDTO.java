package co.com.challenge.sqs.listener.dto;

import co.com.challenge.sqs.listener.helper.CommitDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.*;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;


@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitDTO {
    private String resource;
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
        private RepositoryDTO repository;
        @JsonDeserialize(contentUsing = CommitDeserializer.class)
        private List<Commit> commits;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder(toBuilder = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Commit {
        private String id;
        private LocalDateTime date;
        private String committer;
        private String url;

    }



}

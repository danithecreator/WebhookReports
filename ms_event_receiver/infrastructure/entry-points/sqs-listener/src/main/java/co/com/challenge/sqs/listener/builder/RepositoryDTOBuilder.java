package co.com.challenge.sqs.listener.builder;

import co.com.challenge.sqs.listener.dto.RepositoryDTO;
import com.fasterxml.jackson.databind.JsonNode;

public class RepositoryDTOBuilder {
    public static RepositoryDTO buildRepository(JsonNode repository){
        return RepositoryDTO.builder()
                .id(repository.get("id").asText())
                .name(repository.get("name").asText())
                .url(repository.get("url").asText())
                .build();
    }
}

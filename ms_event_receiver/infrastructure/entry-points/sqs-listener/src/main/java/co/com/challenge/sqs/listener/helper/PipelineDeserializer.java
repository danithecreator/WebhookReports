package co.com.challenge.sqs.listener.helper;

import co.com.challenge.commonutils.DateHelper;
import co.com.challenge.sqs.listener.builder.RepositoryDTOBuilder;
import co.com.challenge.sqs.listener.dto.PipelineDTO;

import co.com.challenge.sqs.listener.dto.RepositoryDTO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDateTime;

public class PipelineDeserializer extends JsonDeserializer<PipelineDTO.Body> {
    @Override
    public PipelineDTO.Body deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        String id = "";
        String url = "";
        String dateString = "";
        String conclusion = "";
        LocalDateTime date;
        RepositoryDTO repositoryDTO = new RepositoryDTO();

        if (node.has("workflow_run") && node.get("workflow_run").has("id")) {
            id = node.get("workflow_run").get("id").asText();
        } else if (node.has("id")) {
            id = node.get("id").asText();
        }

        if (node.has("workflow_run") && node.get("workflow_run").has("url")) {
            url = node.get("workflow_run").get("url").asText();
        } else if (node.has("url")) {
            url = node.get("url").asText();
        }

        if (node.has("workflow_run") && node.get("workflow_run").has("updated_at")) {
            dateString = node.get("workflow_run").get("updated_at").asText();
        } else if (node.has("finishTime")) {
            dateString = node.get("finishTime").asText();
        }
        date = LocalDateTime.parse(DateHelper.parseToStandardFormat(dateString));

        if (node.has("workflow_run") && node.get("workflow_run").has("conclusion")) {
            conclusion = node.get("workflow_run").get("conclusion").asText();
        } else if (node.has("result")) {
            conclusion = node.get("result").asText();
        }

        if(node.has("workflow_run") && node.get("workflow_run").has("repository")){
            repositoryDTO = RepositoryDTOBuilder.buildRepository(node.get("workflow_run").get("repository"));
        } else if(node.has("repository")){
            repositoryDTO = RepositoryDTOBuilder.buildRepository(node.get("repository"));
        }


        return new PipelineDTO.Body(id, url, date, conclusion,repositoryDTO);
    }
}

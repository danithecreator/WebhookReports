package co.com.challenge.sqs.listener.helper;

import co.com.challenge.commonutils.DateHelper;
import co.com.challenge.sqs.listener.builder.RepositoryDTOBuilder;
import co.com.challenge.sqs.listener.dto.PullRequesDTO;
import co.com.challenge.sqs.listener.dto.RepositoryDTO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDateTime;

public class PullRequestDeserializer extends JsonDeserializer<PullRequesDTO.Body> {
    @Override
    public PullRequesDTO.Body deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        String id = "";
        String url = "";
        String dateString = "";
        LocalDateTime date;
        RepositoryDTO repositoryDTO =new RepositoryDTO();


        if (node.has("pull_request") && node.get("pull_request").has("id")) {
            id = node.get("pull_request").get("id").asText();
        }else if(node.has("pullRequestId")){
            id = node.get("pullRequestId").asText();
        }

        if (node.has("pull_request") && node.get("pull_request").has("url")) {
            url = node.get("pull_request").get("url").asText();
        } else if (node.has("url")) {
            url = node.get("url").asText();
        }

        if (node.has("pull_request") && node.get("pull_request").has("closed_at")) {
            dateString = node.get("pull_request").get("closed_at").asText();
        } else if (node.has("closedDate")) {
            dateString = node.get("closedDate").asText();
        }

        if(node.has("repository")){
            repositoryDTO = RepositoryDTOBuilder.buildRepository(node.get("repository"));
        }

        date = LocalDateTime.parse(DateHelper.parseToStandardFormat(dateString));
        return new PullRequesDTO.Body(id,url,date,repositoryDTO);
    }


}

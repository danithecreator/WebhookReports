package co.com.challenge.sqs.listener.helper;

import co.com.challenge.commonutils.DateHelper;
import co.com.challenge.sqs.listener.builder.CommitterDTOBuilder;
import co.com.challenge.sqs.listener.builder.RepositoryDTOBuilder;
import co.com.challenge.sqs.listener.dto.CommitDTO;
import co.com.challenge.sqs.listener.dto.RepositoryDTO;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDateTime;


public  class JsonDeserializerUtil {
    public static String getStringValue(JsonNode node, String... paths) {
        for (String path : paths) {
            String[] parts = path.split("\\.");
            JsonNode current = node;
            for (String part : parts) {
                if (current == null || !current.has(part)) {
                    current = null;
                    break;
                }
                current = current.get(part);
            }
            if (current != null && current.isTextual()) {
                return current.asText();
            }
        }
        return null;
    }

    public static LocalDateTime parseDate(JsonNode node, String... paths) {
        String dateString = getStringValue(node, paths);
        if (dateString != null) {
            return LocalDateTime.parse(DateHelper.parseToStandardFormat(dateString));
        }
        return null;
    }
    public static RepositoryDTO buildRepository(JsonNode node, String... paths) {
        for (String path : paths) {
            if (node.has(path)) {
                return RepositoryDTOBuilder.buildRepository(node.get(path));
            }
        }
        return new RepositoryDTO();
    }

}

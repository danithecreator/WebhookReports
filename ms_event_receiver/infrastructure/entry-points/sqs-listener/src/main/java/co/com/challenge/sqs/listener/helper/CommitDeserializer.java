package co.com.challenge.sqs.listener.helper;

import co.com.challenge.commonutils.DateHelper;
import co.com.challenge.sqs.listener.dto.CommitDTO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDateTime;

import static co.com.challenge.sqs.listener.helper.JsonDeserializerUtil.*;

public class CommitDeserializer extends JsonDeserializer<CommitDTO.Commit> {
    @Override
    public CommitDTO.Commit deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);

        String id = getStringValue(node, "commitId", "id");
        LocalDateTime date = parseDate(node, "committer.date", "timestamp");
        String committer = getStringValue(node, "committer.name");
        String url = getStringValue(node,"url");

        return new CommitDTO.Commit(id, date, committer,url);
    }



}

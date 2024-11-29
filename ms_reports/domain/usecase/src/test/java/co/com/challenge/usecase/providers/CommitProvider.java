package co.com.challenge.usecase.providers;

import co.com.challenge.model.commit.CommitModelRequest;

import co.com.challenge.model.commit.CommitModelResponse;
import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;

@UtilityClass
public class CommitProvider {
    public CommitModelRequest buildCommitModelRequest() {
        return CommitModelRequest.builder()
                .initialDate(OffsetDateTime.now())
                .finishDate(OffsetDateTime.now().plusDays(2))
                .top(5)
                .committersName("user")
                .build();
    }

    public CommitModelResponse buildCommitModelResponse(){
        return CommitModelResponse.builder()
                .total(20)
                .committer("user")
                .build();
    }
}

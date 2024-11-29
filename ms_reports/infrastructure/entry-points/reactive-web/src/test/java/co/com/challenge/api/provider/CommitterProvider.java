package co.com.challenge.api.provider;

import co.com.challenge.api.dtos.request.CommitReportRequest;
import co.com.challenge.model.commit.CommitModelRequest;
import co.com.challenge.model.commit.CommitModelResponse;
import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;

@UtilityClass
public class CommitterProvider {

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

    public CommitReportRequest buildRequestCommit(){
        return CommitReportRequest.builder()
                .committer("user")
                .initialDate(OffsetDateTime.now())
                .finishDate(OffsetDateTime.now().plusDays(1))
                .top(5)
                .build();
    }

}

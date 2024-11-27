package co.com.challenge.usecase.providers;


import co.com.challenge.model.pullrequest.PullRequestModelRequest;
import co.com.challenge.model.pullrequest.PullRequestModelResponse;
import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;

@UtilityClass
public class PullRequestProvider {
    public PullRequestModelRequest buildPullRequestModelRequest(){
        return PullRequestModelRequest.builder()
                .initialDate(OffsetDateTime.now())
                .finishDate(OffsetDateTime.now())
                .build();
    }

    public PullRequestModelResponse buildPullRequestModelResponse(){
        return PullRequestModelResponse.builder()
                .total(5)
                .build();
    }
}

package co.com.challenge.sqs.listener.builder;

import co.com.challenge.model.pullrequestmodel.PullRequestModel;
import co.com.challenge.sqs.listener.dto.PullRequesDTO;

public class PullRequestModelBuilder {
    public static PullRequestModel buildPullRequestModel(PullRequesDTO pullRequesDTO){
        return PullRequestModel.builder()
                .pullRequestId(pullRequesDTO.getBody().getId())
                .date(pullRequesDTO.getBody().getDate())
                .url(pullRequesDTO.getBody().getUrl())
                .repositoryId(pullRequesDTO.getBody().getRepository().getId())
                .build();
    }
}

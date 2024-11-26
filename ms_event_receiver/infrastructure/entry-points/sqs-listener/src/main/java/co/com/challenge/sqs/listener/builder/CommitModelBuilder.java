package co.com.challenge.sqs.listener.builder;

import co.com.challenge.model.commitmodel.CommitModel;
import co.com.challenge.sqs.listener.dto.CommitDTO;
import reactor.core.publisher.Flux;


public class CommitModelBuilder {

    public static Flux<CommitModel> buildCommitModel(CommitDTO commitDTO) {

        return Flux.fromIterable(commitDTO.getBody().getCommits())
                .map(commit -> CommitModel.builder()
                        .commitId(commit.getId())
                        .date(commit.getDate())
                        .committer(commit.getCommitter())
                        .url(commit.getUrl())
                        .build());
    }


}

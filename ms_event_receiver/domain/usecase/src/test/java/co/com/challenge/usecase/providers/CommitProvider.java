package co.com.challenge.usecase.providers;

import co.com.challenge.model.commitmodel.CommitModel;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class CommitProvider {
    public CommitModel builCommitModel(){
        return CommitModel.builder()
                .commitId("15753")
                .url("http://test.com")
                .repositoryId("123456789")
                .committer("userTest")
                .date(LocalDateTime.now())
                .build();
    }

}

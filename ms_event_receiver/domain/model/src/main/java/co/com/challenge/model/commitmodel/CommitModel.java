package co.com.challenge.model.commitmodel;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CommitModel {
    private String commitId;
    private String repositoryId;
    private LocalDateTime date;
    private String committer;
    private String url;
}

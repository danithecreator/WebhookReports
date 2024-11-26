package co.com.challenge.model.pullrequestmodel;
import lombok.*;

import java.time.LocalDateTime;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PullRequestModel {
    private String pullRequestId;
    private String repositoryId;
    private String url;
    private LocalDateTime date;
}

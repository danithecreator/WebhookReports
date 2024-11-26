package co.com.challenge.model.pullrequest;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PullRequestModelResponse {
    private Integer total;
}

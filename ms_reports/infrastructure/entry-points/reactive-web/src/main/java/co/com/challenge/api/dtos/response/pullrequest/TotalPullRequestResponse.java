package co.com.challenge.api.dtos.response.pullrequest;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TotalPullRequestResponse {
    private Integer totalPullRequestCompleted;
}

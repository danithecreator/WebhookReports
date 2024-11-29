package co.com.challenge.model.pullrequest;
import lombok.*;

import java.time.OffsetDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PullRequestModelRequest {
    private OffsetDateTime initialDate;
    private OffsetDateTime finishDate;
}

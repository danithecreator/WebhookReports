package co.com.challenge.model.commit;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class CommitModelRequest {
    private String committersName;
    private OffsetDateTime initialDate;
    private OffsetDateTime finishDate;
    private int top;
}

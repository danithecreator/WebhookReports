package co.com.challenge.model.commit;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class CommitModelRequest {
    private String committersName;
    private String initialDate;
    private String finishDate;
    private int top;

}

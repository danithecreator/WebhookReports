package co.com.challenge.model.commit;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CommitModelResponse {
    private String total;
    private String committer;

}

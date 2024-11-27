package co.com.challenge.model.commit;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class CommitModelResponse {
    private Integer total;
    private String committer;

}

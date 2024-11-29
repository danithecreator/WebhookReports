package co.com.challenge.model.pipelinemodel;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PipelineModel {
    private String pipelineId;
    private String url;
    private LocalDateTime date;
    private String conclusion;
    private String repositoryId;

}

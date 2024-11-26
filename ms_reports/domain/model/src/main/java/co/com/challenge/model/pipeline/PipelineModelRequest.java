package co.com.challenge.model.pipeline;
import lombok.*;

import java.time.OffsetDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class PipelineModelRequest {
    private OffsetDateTime initialDate;
    private OffsetDateTime finishDate;
}

package co.com.challenge.model.pipeline;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class PipelineModelResponse {
    private int total;
    private int successful;
    private int failed;
}

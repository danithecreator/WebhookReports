package co.com.challenge.api.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitReportRequest {

    private String initialDate;
    private String finishDate;
    private String committerName;
    private int top;

}

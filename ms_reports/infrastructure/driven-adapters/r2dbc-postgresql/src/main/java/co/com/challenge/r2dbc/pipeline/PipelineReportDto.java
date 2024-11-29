package co.com.challenge.r2dbc.pipeline;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PipelineReportDto {
    private Integer total;
    private Integer successful;
    private Integer failed;
}
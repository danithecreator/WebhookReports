package co.com.challenge.r2dbc.pipeline;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("\"public\".pipeline")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PipelineDto {

    @Column("pipeline_id")
    private String pipelineId;
    @Column("pipeline_url")
    private String url;
    @Column("pipeline_date")
    private LocalDateTime date;
    @Column("pipeline_status")
    private String conclusion;
    @Column("fk_repository_id")
    private String repositoryId;
}

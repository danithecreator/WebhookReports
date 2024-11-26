package co.com.challenge.r2dbc.pullrequest;

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
@Table("\"public\".pullrequest")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PullRequestDto {

    @Column("pullrequest_id")
    private String pullRequestId;
    @Column("pullrequest_url")
    private String url;
    @Column("pullrequest_date")
    private LocalDateTime date;
    @Column("fk_repository_id")
    private String repositoryId;
}

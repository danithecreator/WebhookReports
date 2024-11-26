package co.com.challenge.r2dbc.commit;

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
@Table("\"public\".commit")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitDto {

    @Column("commit_id")
    private String commitId;
    @Column("commit_date")
    private LocalDateTime date;
    @Column("commit_url")
    private String url;
    @Column("commit_committer")
    private String committer;
    @Column("fk_repository_id")
    private String repositoryId;
}

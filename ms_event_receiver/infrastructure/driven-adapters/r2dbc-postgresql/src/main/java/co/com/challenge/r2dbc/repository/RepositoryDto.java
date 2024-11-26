package co.com.challenge.r2dbc.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("\"public\".repository")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryDto {

    @Column("repository_id")
    private String repositoryId;
    @Column("repository_name")
    private String name;
    @Column("repository_url")
    private String url;
}

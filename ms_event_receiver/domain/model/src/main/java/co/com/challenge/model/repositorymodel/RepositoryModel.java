package co.com.challenge.model.repositorymodel;
import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RepositoryModel {
    private String repositoryId;
    private String name;
    private String url;
}

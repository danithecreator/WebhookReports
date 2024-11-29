package co.com.challenge.sqs.listener.builder;

import co.com.challenge.model.repositorymodel.RepositoryModel;
import co.com.challenge.sqs.listener.dto.RepositoryDTO;

public class RepositoryModelBuilder {
    public static RepositoryModel buildRepositoryModel(RepositoryDTO repositoryDTO){
        return RepositoryModel.builder()
                .repositoryId(repositoryDTO.getId())
                .name(repositoryDTO.getName())
                .url(repositoryDTO.getUrl())
                .build();
    }
}

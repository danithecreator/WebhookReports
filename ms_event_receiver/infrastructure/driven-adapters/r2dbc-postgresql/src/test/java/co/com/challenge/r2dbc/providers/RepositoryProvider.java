package co.com.challenge.r2dbc.providers;

import co.com.challenge.model.repositorymodel.RepositoryModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RepositoryProvider {
    public RepositoryModel buildRepositoryModel(){
        return RepositoryModel.builder()
                .repositoryId("123456789")
                .url("http://test.com")
                .name("testRepo")
                .build();
    }


}

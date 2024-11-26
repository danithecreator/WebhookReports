package co.com.challenge.r2dbc.commit;


import co.com.challenge.model.commitmodel.CommitModel;


import co.com.challenge.r2dbc.providers.CommitProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CommitReactiveRepositoryAdapterTest {

    @Mock
    private CommitReactiveRepository mockRepository;

    @Mock
    private ObjectMapper mockMapper;

    private CommitReactiveRepositoryAdapter repositoryAdapter;

    @BeforeEach
    void setUp() {
        repositoryAdapter = new CommitReactiveRepositoryAdapter(mockRepository, mockMapper);
    }


    @Test
    void storeCommitsDataSuccess() {
        CommitModel commitModel1 = CommitProvider.builCommitModel();
        CommitModel commitModel2 = CommitProvider.builCommitModel();
        Flux<CommitModel> commitList = Flux.just(commitModel1, commitModel2);
        when(mockRepository.saveAll(any(Flux.class))).thenReturn(Flux.empty());
        StepVerifier.create(repositoryAdapter.storeCommitsData(commitList))
                .expectComplete()
                .verify();
        verify(mockRepository).saveAll(any(Flux.class));
    }

}

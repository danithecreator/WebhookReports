package co.com.challenge.usecase.commitreports;

import co.com.challenge.model.commit.CommitModelRequest;
import co.com.challenge.model.commit.gateways.CommitModelRepository;
import co.com.challenge.usecase.providers.CommitProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommitsReportsUseCaseTest {
    @Mock
    private  CommitModelRepository commitModelRepository;
    @InjectMocks
    private CommitReportsUseCase commitReportsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commitReportsUseCase = new CommitReportsUseCase(commitModelRepository);
    }

    @Test
    void getTotalCommitsByDateRangeTest(){
        when(commitModelRepository.getTotalCommitsByDateRange(any())).thenReturn(Mono.just(50L));

        StepVerifier.create(commitReportsUseCase.getTotalCommitsByDateRange(CommitProvider.buildCommitModelRequest()))
                .expectNext(50L)
                .verifyComplete();

        ArgumentCaptor<CommitModelRequest> captor = ArgumentCaptor.forClass(CommitModelRequest.class);
        verify(commitModelRepository).getTotalCommitsByDateRange(captor.capture());

        CommitModelRequest capturedRequest = captor.getValue();
        assertEquals("user", capturedRequest.getCommittersName());
        assertEquals(5, capturedRequest.getTop());
    }


    @Test
    void getTopCommittersByDateRangeTest(){
        when(commitModelRepository.getTopCommittersByDateRange(any())).thenReturn(Flux.just(CommitProvider.buildCommitModelResponse()));

        StepVerifier.create(commitReportsUseCase.getTopCommittersByDateRange(CommitProvider.buildCommitModelRequest()))
                .assertNext(resp ->{
                    assertEquals("user",resp.getCommitter());
                })
                .verifyComplete();

    }
    @Test
    void getTotalCommitsByCommittersNameInDateRangeTest(){
        when(commitModelRepository.getTotalCommitsByCommittersNameInDateRange(any())).thenReturn(Mono.just(CommitProvider.buildCommitModelResponse()));

        StepVerifier.create(commitReportsUseCase.getTotalCommitsByCommittersNameInDateRange(CommitProvider.buildCommitModelRequest()))
                .assertNext(resp ->{
                    assertEquals(20,resp.getTotal());
                })
                .verifyComplete();
    }

}

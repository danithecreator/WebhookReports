package co.com.challenge.api.helper.response.commit;

import co.com.challenge.api.dtos.response.commit.CommitsByCommitterResponse;
import co.com.challenge.api.dtos.response.commit.Committer;
import co.com.challenge.api.dtos.response.commit.TopCommittersResponse;
import co.com.challenge.api.dtos.response.commit.TotalCommitsResponse;
import co.com.challenge.model.commit.CommitModelResponse;
import lombok.experimental.UtilityClass;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UtilityClass
public class CommitResponseBuilder {

    public Mono<ServerResponse> buildTopCommittersResponse(List<CommitModelResponse> commitModelResponseList){
        return ServerResponse
                .ok()
                .bodyValue(buildTopCommittersBodyResponse(commitModelResponseList));
    }

    private TopCommittersResponse buildTopCommittersBodyResponse(List<CommitModelResponse> list){
        return TopCommittersResponse.builder()
                .topCommitters(
                        IntStream.range(0, list.size())
                                .mapToObj(index -> Committer.builder()
                                        .committerName(list.get(index).getCommitter())
                                        .totalCommits(list.get(index).getTotal())
                                        .top(index + 1)
                                        .build())
                                .collect(Collectors.toList()))
                .build();
    }

    public Mono<ServerResponse> buildTotalCommitsResponse(Long totalCommits){
        return ServerResponse
                .ok()
                .bodyValue(TotalCommitsResponse.builder()
                        .totalCommitsMade(totalCommits)
                        .build());
    }

    public Mono<ServerResponse> buildTotalCommitsByCommitterResponse(CommitModelResponse commitModelResponse){
        return ServerResponse
                .ok()
                .bodyValue(CommitsByCommitterResponse
                        .builder()
                        .committer(Committer.builder()
                                .committerName(commitModelResponse.getCommitter())
                                .totalCommits(commitModelResponse.getTotal())
                                .build())
                        .build());
    }
}

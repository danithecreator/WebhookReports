package co.com.challenge.sqs.listener;

import co.com.challenge.sqs.listener.builder.CommitModelBuilder;
import co.com.challenge.sqs.listener.builder.PipelineModelBuilder;
import co.com.challenge.sqs.listener.builder.PullRequestModelBuilder;
import co.com.challenge.sqs.listener.builder.RepositoryModelBuilder;
import co.com.challenge.sqs.listener.dto.CommitDTO;
import co.com.challenge.sqs.listener.dto.PipelineDTO;
import co.com.challenge.sqs.listener.dto.PullRequesDTO;
import co.com.challenge.usecase.commits.CommitsUseCase;
import co.com.challenge.usecase.pipeline.PipelineUseCase;
import co.com.challenge.usecase.pullrequest.PullRequestUseCase;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.model.Message;

import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SQSProcessor implements Function<Message, Mono<Void>> {
    private final CommitsUseCase commitsUseCase;
    private final PipelineUseCase pipelineUseCase;
    private final PullRequestUseCase pullRequestUseCase;

    private final ObjectMapper objectMapperJackson;

    @Override
    public Mono<Void> apply(Message message) {
        try {
            return Mono.just(objectMapperJackson.readValue(message.body(), Map.class))
                    .flatMap(result -> {
                        var eventType = result.get("resource").toString();
                        return switch (eventType) {
                            case "commit" -> {
                                var commitDTO = objectMapperJackson.convertValue(result, CommitDTO.class);
                                yield commitsUseCase.storeCommitsData(
                                                RepositoryModelBuilder.buildRepositoryModel(commitDTO.getBody().getRepository()),
                                                CommitModelBuilder.buildCommitModel(commitDTO))
                                        .then();
                            }
                            case "pullrequest" -> {
                                var pullRequestDTO = objectMapperJackson.convertValue(result, PullRequesDTO.class);
                                yield pullRequestUseCase.storePullRequestData(RepositoryModelBuilder
                                                        .buildRepositoryModel(pullRequestDTO.getBody().getRepository()),
                                                PullRequestModelBuilder.buildPullRequestModel(pullRequestDTO)
                                        )
                                        .then();
                            }
                            case "pipeline" -> {
                                var pipelineDTO = objectMapperJackson.convertValue(result, PipelineDTO.class);
                                yield pipelineUseCase.storePipelineData(RepositoryModelBuilder
                                                        .buildRepositoryModel(pipelineDTO.getBody().getRepository()),
                                                PipelineModelBuilder.buildPipelineModel(pipelineDTO)
                                        )
                                        .then();
                            }
                            default -> Mono.empty();
                        };
                    })
                    .then();
        } catch (JsonProcessingException e) {
            return Mono.error(e);
        }
    }


}
////objectMapperJackson.readValue(message.body(), Map.class
//
//                        switch(result.
//
//get("resource").
//
//toString()){
//        case"commit":
//var commitDTO = objectMapperJackson.convertValue(result, CommitDTO.class);
//                                return commitsUseCase.
//
//storeCommitsData(
//        RepositoryModelBuilder.buildRepositoryModel(commitDTO.getBody().
//
//getRepository()),
//        CommitModelBuilder.
//
//buildCommitModel(commitDTO));
//        case"pullrequest":
//var pullRequestDTO = objectMapperJackson.convertValue(result, PullRequesDTO.class);
//                                return pullRequestUseCase.
//
//storePullRequestData(
//        PullRequestModelBuilder.buildPullRequestModel(pullRequestDTO)
//                                );
//
//                                        case"pipeline":
//var pipelineDTO = objectMapperJackson.convertValue(result, PipelineDTO.class);
//                                return pipelineUseCase.
//
//storePipelineData(
//        PipelineModelBuilder.builPipelineModel(pipelineDTO)
//                                );
//default:
//        return Mono.
//
//empty();
//                        }
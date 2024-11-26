package co.com.challenge.api.helper.request;

import co.com.challenge.api.dtos.request.CommitReportRequest;
import co.com.challenge.api.dtos.request.PipelineReportRequest;
import co.com.challenge.api.dtos.request.PullRequestReportRequest;
import co.com.challenge.model.commit.CommitModelRequest;
import co.com.challenge.model.pipeline.PipelineModelRequest;
import co.com.challenge.model.pullrequest.PullRequestModelRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ModelBuilder {
    public  CommitModelRequest buildCommitModel(CommitReportRequest commitReportRequest){
        return CommitModelRequest.builder()
                .committersName(defaultValue(commitReportRequest.getCommitter(), ""))
                .initialDate(commitReportRequest.getInitialDate())
                .finishDate(commitReportRequest.getFinishDate())
                .top(defaultValue(commitReportRequest.getTop(), 5))
                .build();
    }

    public PullRequestModelRequest buildPullrequestModel(PullRequestReportRequest pullRequestReportRequest){
        return PullRequestModelRequest.builder()
                .initialDate(pullRequestReportRequest.getInitialDate())
                .finishDate(pullRequestReportRequest.getFinishDate())
                .build();
    }

    public PipelineModelRequest buildPipelineRequest(PipelineReportRequest pipelineReportRequest){
        return PipelineModelRequest.builder()
                .initialDate(pipelineReportRequest.getInitialDate())
                .finishDate(pipelineReportRequest.getFinishDate())
                .build();
    }

    private static   <T> T defaultValue(T obj, T defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        return obj;
    }


}

package co.com.challenge.api.helper;

import co.com.challenge.api.dtos.request.CommitReportRequest;
import co.com.challenge.model.commit.CommitModelRequest;

public class ModelBuilder {
    public static CommitModelRequest buildCommitModel(CommitReportRequest commitReportRequest){
        return CommitModelRequest.builder()
                .committersName(defaultValue(commitReportRequest.getCommitterName(), ""))
                .initialDate(defaultValue(commitReportRequest.getInitialDate(), ""))
                .finishDate(defaultValue(commitReportRequest.getFinishDate(), ""))
                .top(defaultValue(commitReportRequest.getTop(), 5))
                .build();
    }

    private static   <T> T defaultValue(T obj, T defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        return obj;
    }
}

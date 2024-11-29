package co.com.challenge.model.constants;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;


public class Constants {
    private Constants() {
        throw new IllegalStateException("Constants class");
    }

    public static final String BASE_PATH = "/webhook/report";
    public static final String OPERATION_GET_TOP_COMMITTERS = "/get-top-committers";
    public static final String OPERATION_GET_TOTAL_COMMITS = "/get-total-commits";
    public static final String OPERATION_GET_TOTAL_COMMITS_BY_COMMITTER = "/get-total-commits-by-committer";
    public static final String OPERATION_GET_TOTAL_CLOSED_PR = "/get-total-closed-pr";
    public static final String OPERATION_GET_TOTAL_PIPELINES_RUNS = "/get-total-pipelines-runs";

}

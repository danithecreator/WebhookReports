package co.com.challenge.api;

import co.com.challenge.model.constants.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return nest(path(Constants.BASE_PATH),
                route(POST(Constants.OPERATION_GET_TOP_COMMITTERS),handler::listenPOSTTopCommitters)
                        .andRoute(POST(Constants.OPERATION_GET_TOTAL_COMMITS),handler::listenPOSTTotalCommits)
                        .andRoute(POST(Constants.OPERATION_GET_TOTAL_COMMITS_BY_COMMITTER),handler::listenPOSTTotalCommitsByCommitter)
                        .andRoute(POST(Constants.OPERATION_GET_TOTAL_CLOSED_PR),handler::listenPOSTTotalClosedPr)
                        .andRoute(POST(Constants.OPERATION_GET_TOTAL_PIPELINES_RUNS),handler::listenPOSTTotalPipelinesRuns)
        );
    }
}

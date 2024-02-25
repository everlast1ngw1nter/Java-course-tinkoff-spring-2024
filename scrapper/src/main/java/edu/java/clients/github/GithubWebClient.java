package edu.java.clients.github;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GithubWebClient {

    private final WebClient githubClient;

    public Mono<Response> fetchRepo(String owner, String repo) {
        return githubClient
                .get()
                .uri("/{owner}/{repo}", owner, repo)
                .retrieve()
                .bodyToMono(Response.class);
    }
}

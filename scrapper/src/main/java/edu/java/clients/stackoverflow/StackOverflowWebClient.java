package edu.java.clients.stackoverflow;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StackOverflowWebClient {

    private final WebClient stackOverflowClient;

    public Mono<ItemsList> fetchQuestion(String name) {
        return stackOverflowClient
                .get()
                .uri("/{name}/?site=stackoverflow", name)
                .retrieve()
                .bodyToMono(ItemsList.class);
    }
}

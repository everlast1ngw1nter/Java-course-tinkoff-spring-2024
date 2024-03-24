package edu.java.clients.stackoverflow;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StackOverflowWebClient {

    private final WebClient stackOverflowClient;

    public Mono<ItemsList> fetchQuestion(Long id) {
        return stackOverflowClient
                .get()
                .uri("/{id}/?site=stackoverflow", id)
                .retrieve()
                .bodyToMono(ItemsList.class);
    }
}

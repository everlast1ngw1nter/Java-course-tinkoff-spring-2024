package edu.java.clients;

import edu.java.models.requests.LinkUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class BotWebClient {

    private final WebClient botClient;

    public String sendUpdates(LinkUpdateRequest linkUpdateRequest) {
        return botClient
                .post()
                .uri("/updates")
                .body(BodyInserters.fromValue(linkUpdateRequest))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}

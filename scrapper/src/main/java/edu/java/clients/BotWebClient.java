package edu.java.clients;

import edu.java.models.requests.LinkUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class BotWebClient {

    private final WebClient botClient;

    public void sendUpdates(LinkUpdateRequest linkUpdateRequest) {

    }
}

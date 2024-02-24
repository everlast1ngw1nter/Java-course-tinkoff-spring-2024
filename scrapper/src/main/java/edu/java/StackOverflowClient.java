package edu.java;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class StackOverflowClient {

    private final WebClient webClient;

    public StackOverflowClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.stackexchange.com/2.3/posts").build();
    }

    public WebClient.ResponseSpec someRestCall(String name) {
        WebClient.ResponseSpec responseSpec = this.webClient
                .get()
                .uri("/{name}/comments?site=stackoverflow", name)
                .retrieve();
        return responseSpec;
    }
}

package edu.java.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class ClientConfiguration {
    private final ApplicationConfig applicationConfig;

    @Bean
    public WebClient githubClient() {
        return WebClient
                .builder()
                .baseUrl(applicationConfig.githubBaseUrl())
                .build();
    }

    @Bean
    public WebClient stackOverflowClient() {
        return WebClient
                .builder()
                .baseUrl(applicationConfig.stackOverflowBaseUrl())
                .build();
    }
}

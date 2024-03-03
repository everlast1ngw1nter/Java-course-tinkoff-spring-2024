package edu.java.bot.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class ClientConfiguration {
    private final ApplicationConfig applicationConfig;

    @Bean
    public WebClient scrapperClient() {
        return WebClient
                .builder()
                .baseUrl(applicationConfig.scrapperBaseUrl())
                .build();
    }
}

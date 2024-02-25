package edu.java.scrapper;

import com.github.tomakehurst.wiremock.WireMockServer;
import edu.java.clients.stackoverflow.StackOverflowWebClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

public class StackOverflowClientTest {

    private StackOverflowWebClient client;
    private WireMockServer wireMockServer;

    private static final String BODY = """
            
            """;

    @BeforeEach
    void prep(){
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        client = new StackOverflowWebClient(
                WebClient
                .builder()
                .baseUrl("http://localhost:" + wireMockServer.port())
                .build());
    }

    @Test
    void fetchQuestionTest() {

    }
}

package edu.java.scrapper;

import com.github.tomakehurst.wiremock.WireMockServer;
import edu.java.clients.stackoverflow.StackOverflowWebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static org.assertj.core.api.Assertions.assertThat;

public class StackOverflowClientTest {

    private StackOverflowWebClient client;
    private WireMockServer wireMockServer;

    private static final String BODY = """
            {
                "items": [
                    {
                        "tags": [
                            "intellij-idea",
                            "intellij-15"
                        ],
                        "owner": {
                            "account_id": 5029525,
                            "reputation": 357,
                            "user_id": 4040212,
                            "user_type": "registered",
                            "profile_image": "https://www.gravatar.com/avatar/88670a6c1fe1222df0af72f23fb5963d?s=256&d=identicon&r=PG&f=y&so-version=2",
                            "display_name": "Astrid Yu",
                            "link": "https://stackoverflow.com/users/4040212/astrid-yu"
                        },
                        "is_answered": true,
                        "view_count": 8335,
                        "accepted_answer_id": 44758847,
                        "answer_count": 2,
                        "score": 18,
                        "last_activity_date": 1498477406,
                        "creation_date": 1448413971,
                        "question_id": 33906873,
                        "content_license": "CC BY-SA 3.0",
                        "link": "https://stackoverflow.com/questions/33906873/how-do-you-change-highlighting-for-all-files-in-intellij",
                        "title": "How do you change highlighting for all files in IntelliJ?"
                    }
                ],
                "has_more": false,
                "quota_max": 300,
                "quota_remaining": 297
            }
            """;

    @BeforeEach
    void prep() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        client = new StackOverflowWebClient(
                WebClient
                        .builder()
                        .baseUrl("http://localhost:" + wireMockServer.port() + "/2.3/questions/")
                        .build());
    }

    @Test
    void fetchQuestionTest() {
        stubFor(get(urlEqualTo("/2.3/questions/33906873?site=stackoverflow"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(BODY))
        );
        var resp = client.fetchQuestion(33906873L).block();

        assertThat(resp.items().size())
                .isEqualTo(1);
    }
}

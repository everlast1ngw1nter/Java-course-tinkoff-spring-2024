package edu.java.bot.clients;

import edu.java.models.requests.AddLinkRequest;
import edu.java.models.requests.RemoveLinkRequest;
import edu.java.models.responses.LinkResponse;
import edu.java.models.responses.ListLinksResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ScrapperWebClient {

    private final WebClient scrapperClient;

    private static final String CHAT_PATH = "/tg-chat/";

    private static final String LINKS_PATH = "/links";

    private static final String TG_CHAT_HEADER = "Tg-Chat-Id";

    public String registerChat(long id) {
        var uri = CHAT_PATH + id;
        return scrapperClient
                .post()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String deleteChat(long id) {
        var uri = CHAT_PATH + id;
        return scrapperClient
                .delete()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public ListLinksResponse getLinks(long id) {
        return scrapperClient
                .get()
                .uri(LINKS_PATH)
                .header(TG_CHAT_HEADER, String.valueOf(id))
                .retrieve()
                .bodyToMono(ListLinksResponse.class)
                .block();

    }

    public LinkResponse addLink(long id, AddLinkRequest addLinkRequest) {
        return scrapperClient
                .post()
                .uri(LINKS_PATH)
                .header(TG_CHAT_HEADER, String.valueOf(id))
                .body(BodyInserters.fromValue(addLinkRequest))
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .block();
    }

    public LinkResponse removeLink(long id, RemoveLinkRequest removeLinkRequest) {
        return scrapperClient
                .method(HttpMethod.DELETE)
                .uri(LINKS_PATH)
                .header(TG_CHAT_HEADER, String.valueOf(id))
                .body(BodyInserters.fromValue(removeLinkRequest))
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .block();
    }
}

package edu.java.contollers;

import edu.java.domain.services.LinkService;
import edu.java.domain.services.TgChatService;
import edu.java.models.requests.AddLinkRequest;
import edu.java.models.requests.RemoveLinkRequest;
import edu.java.models.responses.LinkResponse;
import edu.java.models.responses.ListLinksResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@SuppressWarnings("MultipleStringLiterals")
public class ScrapperController {

    private final LinkService jdbcLinkService;

    private final TgChatService jdbcTgChatService;

    private static final Logger LOGGER = LogManager.getLogger();

    @PostMapping("/tg-chat/{id}")
    void registerChat(@PathVariable Long id) {
        LOGGER.info("register chat by " + id);
        jdbcTgChatService.register(id);
    }

    @DeleteMapping("/tg-chat/{id}")
    void deleteChat(@PathVariable Long id) {
        LOGGER.info("delete chat by " + id);
        jdbcTgChatService.unregister(id);
    }

    @GetMapping("/links")
    ListLinksResponse getLinks(@RequestHeader("Tg-Chat-Id") int tgChatId) {
        LOGGER.info("get links by " + tgChatId);
        return jdbcLinkService.listAll(tgChatId);
    }

    @PostMapping("/links")
    LinkResponse addLink(@RequestHeader("Tg-Chat-Id") int tgChatId, @RequestBody AddLinkRequest linkRequest) {
        LOGGER.info("add link " + linkRequest.link() + " by " + tgChatId);
        jdbcLinkService.add(tgChatId, linkRequest.link());
        return new LinkResponse(tgChatId, linkRequest.link());
    }

    @DeleteMapping("/links")
    LinkResponse removeLink(@RequestHeader("Tg-Chat-Id") int tgChatId, @RequestBody RemoveLinkRequest linkRequest) {
        LOGGER.info("remove link " + linkRequest.link() + " by " + tgChatId);
        jdbcLinkService.remove(tgChatId, linkRequest.link());
        return new LinkResponse(tgChatId, linkRequest.link());
    }
}

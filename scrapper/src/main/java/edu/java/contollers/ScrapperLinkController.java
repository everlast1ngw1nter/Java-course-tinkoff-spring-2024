package edu.java.contollers;

import edu.java.models.requests.AddLinkRequest;
import edu.java.models.requests.RemoveLinkRequest;
import edu.java.models.responses.LinkResponse;
import edu.java.models.responses.ListLinksResponse;
import edu.java.services.LinkService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@SuppressWarnings("MultipleStringLiterals")
public class ScrapperLinkController {

    private final LinkService jdbcLinkService;

    private static final Logger LOGGER = LogManager.getLogger();

    @GetMapping("/links")
    ListLinksResponse getLinks(@RequestHeader("Tg-Chat-Id") long tgChatId) {
        LOGGER.info("get links by " + tgChatId);
        return jdbcLinkService.listAll(tgChatId);
    }

    @PostMapping("/links")
    LinkResponse addLink(@RequestHeader("Tg-Chat-Id") long tgChatId, @RequestBody AddLinkRequest linkRequest) {
        LOGGER.info("add link " + linkRequest.link() + " by " + tgChatId);
        jdbcLinkService.add(tgChatId, linkRequest.link());
        return new LinkResponse(tgChatId, linkRequest.link(), tgChatId);
    }

    @DeleteMapping("/links")
    LinkResponse removeLink(@RequestHeader("Tg-Chat-Id") long tgChatId, @RequestBody RemoveLinkRequest linkRequest) {
        LOGGER.info("remove link " + linkRequest.link() + " by " + tgChatId);
        jdbcLinkService.remove(tgChatId, linkRequest.link());
        return new LinkResponse(tgChatId, linkRequest.link(), tgChatId);
    }
}

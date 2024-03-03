package edu.java;

import edu.java.models.requests.AddLinkRequest;
import edu.java.models.requests.RemoveLinkRequest;
import edu.java.models.responses.LinkResponse;
import edu.java.models.responses.ListLinksResponse;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScrapperController {

    private final static Logger LOGGER = LogManager.getLogger();

    @PostMapping("/tg-chat/{id}")
    void registerChat(@PathVariable Long id) {
        LOGGER.info("register chat by " + id);
    }

    @DeleteMapping("/tg-chat/{id}")
    void deleteChat(@PathVariable Long id) {
        LOGGER.info("delete chat by " + id);
    }

    @GetMapping("/links")
    ListLinksResponse getLinks(@RequestHeader("Tg-Chat-Id") int tgChatId) {
        LOGGER.info("get links by " + tgChatId);
        return new ListLinksResponse(new ArrayList<>(), -1);
    }

    @PostMapping("/links")
    LinkResponse addLink(@RequestHeader("Tg-Chat-Id") int tgChatId, @RequestBody AddLinkRequest linkRequest) {
        LOGGER.info("add link " + linkRequest.link() + " by " + tgChatId);
        return new LinkResponse(tgChatId, linkRequest.link());
    }

    @DeleteMapping("/links")
    LinkResponse removeLink(@RequestHeader("Tg-Chat-Id") int tgChatId, @RequestBody RemoveLinkRequest linkRequest) {
        LOGGER.info("remove link " + linkRequest.link() + " by " + tgChatId);
        return new LinkResponse(tgChatId, linkRequest.link());
    }
}

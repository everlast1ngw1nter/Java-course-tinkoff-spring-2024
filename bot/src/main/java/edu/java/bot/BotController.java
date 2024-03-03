package edu.java.bot;

import edu.java.bot.requests.LinkUpdateRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BotController {

    private final static Logger LOGGER = LogManager.getLogger();

    @PostMapping("/updates")
    LinkUpdateRequest getUpdate(@RequestBody LinkUpdateRequest updateBody) {
        LOGGER.info(updateBody.toString());
        return updateBody;
    }
}

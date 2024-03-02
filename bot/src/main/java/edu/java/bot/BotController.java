package edu.java.bot;

import edu.java.bot.responces.LinkUpdate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BotController {

    private final static Logger LOGGER = LogManager.getLogger();

    @PostMapping("/updates")
    LinkUpdate getUpdate(@RequestBody LinkUpdate updateBody) {
        LOGGER.info(updateBody.toString());
        return updateBody;
    }
}

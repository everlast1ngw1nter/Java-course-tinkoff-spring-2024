package edu.java.bot.messageprocessor;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.clients.ScrapperWebClient;

public class StartProcessor extends AbstractProcessor {

    private final ScrapperWebClient scrapperWebClient;

    public StartProcessor(AbstractProcessor nextProcessor, ScrapperWebClient scrapperWebClient) {
        super(nextProcessor);
        this.scrapperWebClient = scrapperWebClient;
    }

    @Override
    public String process(Update elem) {
        if (elem.message().text().strip().equals("/start")) {
            return "The user is registered";
        }
        return nextMessageProcessor.process(elem);
    }
}

package edu.java.bot.messageprocessor;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.clients.ScrapperWebClient;

public class ListProcessor extends AbstractProcessor {

    private final ScrapperWebClient scrapperWebClient;

    public ListProcessor(AbstractProcessor nextProcessor, ScrapperWebClient scrapperWebClient) {
        super(nextProcessor);
        this.scrapperWebClient = scrapperWebClient;
    }

    @Override
    public String process(Update elem) {
        if (elem.message().text().strip().equals("/list")) {
            return "The list of links is shown";
        }
        return nextMessageProcessor.process(elem);
    }
}

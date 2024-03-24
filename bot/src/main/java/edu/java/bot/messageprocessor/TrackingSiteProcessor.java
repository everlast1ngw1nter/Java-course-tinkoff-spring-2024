package edu.java.bot.messageprocessor;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.Bot;
import edu.java.bot.BotStatus;
import edu.java.bot.clients.ScrapperWebClient;

public class TrackingSiteProcessor extends AbstractProcessor {

    private final ScrapperWebClient scrapperWebClient;

    public TrackingSiteProcessor(AbstractProcessor nextProcessor, ScrapperWebClient scrapperWebClient) {
        super(nextProcessor);
        this.scrapperWebClient = scrapperWebClient;
    }

    @Override
    public String process(Update elem) {
        if (elem.message().text().strip().startsWith("http")
                && Bot.BOT_STATUS_MAP.get(elem.message().chat().id()) == BotStatus.START_TRACKING) {
            Bot.BOT_STATUS_MAP.put(elem.message().chat().id(), BotStatus.DEFAULT);
            return "Link tracking started";
        }
        return nextMessageProcessor.process(elem);
    }
}

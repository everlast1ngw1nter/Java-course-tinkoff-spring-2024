package edu.java.bot.messageprocessor;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.Bot;
import edu.java.bot.BotStatus;
import edu.java.bot.clients.ScrapperWebClient;

public class UntrackingSiteProcessor extends AbstractProcessor {
    private final ScrapperWebClient scrapperWebClient;

    public UntrackingSiteProcessor(AbstractProcessor nextProcessor, ScrapperWebClient scrapperWebClient) {
        super(nextProcessor);
        this.scrapperWebClient = scrapperWebClient;
    }

    @Override
    public String process(Update elem) {
        if (elem.message().text().strip().startsWith("http")
                && Bot.BOT_STATUS_MAP.get(elem.message().chat().id()) == BotStatus.START_UNTRACKING) {
            Bot.BOT_STATUS_MAP.put(elem.message().chat().id(), BotStatus.DEFAULT);
            return "Link tracking finished";
        }
        return nextMessageProcessor.process(elem);
    }
}

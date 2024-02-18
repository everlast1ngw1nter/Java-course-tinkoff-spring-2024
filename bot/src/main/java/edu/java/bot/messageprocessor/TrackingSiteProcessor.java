package edu.java.bot.messageprocessor;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.Bot;
import edu.java.bot.BotStatus;

public class TrackingSiteProcessor extends AbstractProcessor {
    public TrackingSiteProcessor(AbstractProcessor nextProcessor) {
        super(nextProcessor);
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

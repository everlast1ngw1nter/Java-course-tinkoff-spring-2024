package edu.java.bot.messageprocessor;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.Bot;
import edu.java.bot.BotStatus;

public class TrackProcessor extends AbstractProcessor {

    public TrackProcessor(AbstractProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public String process(Update elem) {
        if (elem.message().text().strip().equals("/track")) {
            Bot.BOT_STATUS_MAP.put(elem.message().chat().id(), BotStatus.START_TRACKING);
            return "Enter the tracking link";
        }
        return nextMessageProcessor.process(elem);
    }
}

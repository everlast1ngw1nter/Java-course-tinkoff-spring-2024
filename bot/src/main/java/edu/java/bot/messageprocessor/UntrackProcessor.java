package edu.java.bot.messageprocessor;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.Bot;
import edu.java.bot.BotStatus;

public class UntrackProcessor extends AbstractProcessor {

    public UntrackProcessor(AbstractProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public String process(Update elem) {
        if (elem.message().text().strip().equals("/untrack")) {
            Bot.BOT_STATUS_MAP.put(elem.message().chat().id(), BotStatus.START_UNTRACKING);
            return "/untrack";
        }
        return nextMessageProcessor.process(elem);
    }
}

package edu.java.bot.messageprocessor;

import com.pengrad.telegrambot.model.Update;

public class UntrackProcessor extends AbstractProcessor {

    public UntrackProcessor(AbstractProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public String process(Update elem) {
        if (elem.message().text().strip().equals("/untrack")) {
            return "/untrack";
        }
        return nextMessageProcessor.process(elem);
    }
}

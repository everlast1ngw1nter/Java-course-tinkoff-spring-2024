package edu.java.bot.messageprocessor;

import com.pengrad.telegrambot.model.Update;

public class StartProcessor extends AbstractProcessor {

    public StartProcessor(AbstractProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public String process(Update elem) {
        if (elem.message().text().strip().equals("/start")) {
            return "/start";
        }
        return nextMessageProcessor.process(elem);
    }
}

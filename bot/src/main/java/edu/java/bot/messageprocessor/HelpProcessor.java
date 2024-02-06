package edu.java.bot.messageprocessor;

import com.pengrad.telegrambot.model.Update;

public class HelpProcessor extends AbstractProcessor {

    public HelpProcessor(AbstractProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public String process(Update elem) {
        if (elem.message().text().strip().equals("/help")) {
            return "/help";
        }
        return nextMessageProcessor.process(elem);
    }
}

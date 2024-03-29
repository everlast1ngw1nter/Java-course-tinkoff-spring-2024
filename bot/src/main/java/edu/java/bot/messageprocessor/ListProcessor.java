package edu.java.bot.messageprocessor;

import com.pengrad.telegrambot.model.Update;

public class ListProcessor extends AbstractProcessor {

    public ListProcessor(AbstractProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public String process(Update elem) {
        if (elem.message().text().strip().equals("/list")) {
            return "The list of links is shown";
        }
        return nextMessageProcessor.process(elem);
    }
}

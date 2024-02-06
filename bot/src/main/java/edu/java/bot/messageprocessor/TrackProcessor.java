package edu.java.bot.messageprocessor;

import com.pengrad.telegrambot.model.Update;

public class TrackProcessor extends AbstractProcessor {

    public TrackProcessor(AbstractProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public String process(Update elem) {
        if (elem.message().text().strip().equals("/track")) {
            return "/track";
        }
        return nextMessageProcessor.process(elem);
    }
}

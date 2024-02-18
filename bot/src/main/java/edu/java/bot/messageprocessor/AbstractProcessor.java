package edu.java.bot.messageprocessor;

import com.pengrad.telegrambot.model.Update;

public abstract class AbstractProcessor {

    AbstractProcessor nextMessageProcessor;

    public AbstractProcessor(AbstractProcessor nextProcessor) {
        nextMessageProcessor =  nextProcessor;
    }

    abstract public String process(Update elem);
}

package edu.java.bot.messageprocessor;

public abstract class AbstractProcessor {

    private AbstractProcessor nextMessageProcessor;

    public AbstractProcessor setNextProcessor(AbstractProcessor nextProcessor) {
        nextMessageProcessor = nextProcessor;
        return nextProcessor;
    }

    abstract public String process();
}

package edu.java.bot.messageprocessor;

import com.pengrad.telegrambot.model.Update;

public class UnknownProcessor extends AbstractProcessor {

    public UnknownProcessor(AbstractProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public String process(Update elem) {
        return "Получена неизвестная команда";
    }
}

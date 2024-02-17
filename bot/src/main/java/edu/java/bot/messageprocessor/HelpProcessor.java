package edu.java.bot.messageprocessor;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;

public class HelpProcessor extends AbstractProcessor {

    public HelpProcessor(AbstractProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public String process(Update elem) {
        if (elem.message().text().strip().equals("/help")) {
            return """
                    /start - Start the bot
                    /track - Add new site for tracking
                    /untrack - Stop site tracking
                    /list - Show the list of tracked links
                    /help - Get help""";
        }
        return nextMessageProcessor.process(elem);
    }
}

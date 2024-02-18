package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.messageprocessor.ProcessorHolder;
import java.util.HashMap;
import java.util.Map;

public class Bot {

    private final TelegramBot telegramBot;

    public static final Map<Long, BotStatus> BOT_STATUS_MAP = new HashMap<>();

    public Bot(ApplicationConfig config) {
        telegramBot = new TelegramBot(config.telegramToken());
    }

    public void start() {
        TelegramBotService.setMenuCommands(telegramBot);
        telegramBot.setUpdatesListener(updates -> {
            for (var elem : updates) {
                var res = ProcessorHolder.PROCESSOR.process(elem);
                var id = elem.message().chat().id();
                SendMessage request = new SendMessage(id, res);
                telegramBot.execute(request);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}

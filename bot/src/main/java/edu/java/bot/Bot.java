package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.clients.ScrapperWebClient;
import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.messageprocessor.ProcessorHolder;
import java.util.HashMap;
import java.util.Map;

public class Bot {

    private final TelegramBot telegramBot;

    private final ScrapperWebClient scrapperWebClient;

    public static final Map<Long, BotStatus> BOT_STATUS_MAP = new HashMap<>();

    public Bot(ApplicationConfig config, ScrapperWebClient scrapperWebClient) {
        telegramBot = new TelegramBot(config.telegramToken());
        this.scrapperWebClient = scrapperWebClient;
    }

    public void start() {
        TelegramBotService.setMenuCommands(telegramBot);
        var processor = ProcessorHolder.getProcessor(scrapperWebClient);
        telegramBot.setUpdatesListener(updates -> {
            for (var elem : updates) {
                var res = processor.process(elem);
                var id = elem.message().chat().id();
                SendMessage request = new SendMessage(id, res);
                telegramBot.execute(request);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}

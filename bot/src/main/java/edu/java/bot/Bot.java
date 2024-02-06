package edu.java.bot;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import edu.java.bot.configuration.ApplicationConfig;
import java.io.IOException;
import java.util.List;

public class Bot {

    private final TelegramBot telegramBot;

    public Bot(ApplicationConfig config) {
        telegramBot = new TelegramBot(config.telegramToken());
        System.out.println("Bot is ready");
    }

    public void start() {
        telegramBot.setUpdatesListener(new UpdatesListener() {
            @Override
            public int process(List<Update> list) {
                for (var elem : list) {
                    var id = elem.message().chat().id();
                    SendMessage request = new SendMessage(id, "text");
                    telegramBot.execute(request, new Callback<SendMessage, SendResponse>() {
                        @Override
                        public void onResponse(SendMessage request, SendResponse response) {

                        }

                        @Override
                        public void onFailure(SendMessage request, IOException e) {

                        }
                    });
                }
                return CONFIRMED_UPDATES_ALL;
            }
        });
    }
}

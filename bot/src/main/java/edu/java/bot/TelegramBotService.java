package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SetMyCommands;

public class TelegramBotService {

    private TelegramBotService() {
    }

    public static void setMenuCommands(TelegramBot bot) {

        BotCommand[] commands = new BotCommand[]{
                new BotCommand("start", "Start the bot"),
                new BotCommand("track", "Add new site for tracking"),
                new BotCommand("untrack", "Stop site tracking"),
                new BotCommand("list", "Show the list of tracked links"),
                new BotCommand("help", "Get help")
        };

        SetMyCommands setMyCommands = new SetMyCommands(commands);
        bot.execute(setMyCommands);
    }
}

package edu.java.bot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import edu.java.bot.messageprocessor.ProcessorHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProcessorsTest {

    private Update update;
    private Chat chat;
    private Message message;

    @BeforeEach
    void initMocks() {
        update = mock(Update.class);
        message =  mock(Message.class);
        chat = mock(Chat.class);
        var chatId = 1L;
        when(update.message()).thenReturn(message);
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(chatId);
    }

    @Test
    public void helpProcessorTest() {
        when(message.text()).thenReturn("/help");
        var text = ProcessorHolder.PROCESSOR.process(update);
        assertEquals("""
                    /start - Start the bot
                    /track - Add new site for tracking
                    /untrack - Stop site tracking
                    /list - Show the list of tracked links
                    /help - Get help""", text);
    }

    @Test
    public void listProcessorTest() {
        when(message.text()).thenReturn("/list");
        var text = ProcessorHolder.PROCESSOR.process(update);
        assertEquals("The list of links is shown", text);
    }

    @Test
    public void startProcessorTest() {
        when(message.text()).thenReturn("/start");
        var text = ProcessorHolder.PROCESSOR.process(update);
        assertEquals("The user is registered", text);
    }

    @Test
    public void trackingSiteProcessorTest() {
        Bot.BOT_STATUS_MAP.put(1L, BotStatus.START_TRACKING);
        when(message.text()).thenReturn("http");
        var text = ProcessorHolder.PROCESSOR.process(update);
        assertEquals("Link tracking started", text);
    }

    @Test
    public void trackProcessorTest() {
        when(message.text()).thenReturn("/track");
        var text = ProcessorHolder.PROCESSOR.process(update);
        assertEquals("Enter the tracking link", text);
    }

    @Test
    public void unknownProcessorTest() {
        when(message.text()).thenReturn("/randomword");
        var text = ProcessorHolder.PROCESSOR.process(update);
        assertEquals("An unknown command was received", text);
    }


    @Test
    public void untrackProcessorTest() {
        when(message.text()).thenReturn("/untrack");
        var text = ProcessorHolder.PROCESSOR.process(update);
        assertEquals("Enter the link to stop tracking", text);
    }

    @Test
    public void untrackingSiteProcessorTest() {
        Bot.BOT_STATUS_MAP.put(1L, BotStatus.START_UNTRACKING);
        when(message.text()).thenReturn("http");
        var text = ProcessorHolder.PROCESSOR.process(update);
        assertEquals("Link tracking finished", text);
    }
}

package edu.java.services.jpa;

import edu.java.domain.jpadao.JpaChatDao;
import edu.java.domain.jpadao.models.Chat;
import edu.java.services.TgChatService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaTgChatService implements TgChatService {

    private final JpaChatDao jpaChatDao;

    @Override
    public void register(long tgChatId) {
        var chat = new Chat();
        chat.setId(tgChatId);
        jpaChatDao.saveAndFlush(chat);
    }

    @Override
    public void unregister(long tgChatId) {
        jpaChatDao.deleteChatById(tgChatId);
    }
}

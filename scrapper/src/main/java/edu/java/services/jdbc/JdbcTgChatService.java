package edu.java.services.jdbc;

import edu.java.domain.ChatDao;
import edu.java.services.TgChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JdbcTgChatService implements TgChatService {

    private final ChatDao chatDao;

    @Override
    public void register(long tgChatId) {
        chatDao.add(tgChatId);
    }

    @Override
    public void unregister(long tgChatId) {
        chatDao.delete(tgChatId);
    }
}

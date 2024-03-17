package edu.java.domain.services.jdbc;

import edu.java.domain.ChatDao;
import edu.java.domain.services.TgChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JdbcTgChatService implements TgChatService {

    private final JdbcTemplate jdbcTemplateScrapper;

    @Override
    public void register(long tgChatId) {
        ChatDao.add(jdbcTemplateScrapper, tgChatId);
    }

    @Override
    public void unregister(long tgChatId) {
        ChatDao.delete(jdbcTemplateScrapper, tgChatId);
    }
}

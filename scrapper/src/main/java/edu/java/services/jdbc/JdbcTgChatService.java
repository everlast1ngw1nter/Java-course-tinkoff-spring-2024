package edu.java.services.jdbc;

import edu.java.domain.jdbcdao.JdbcChatDao;
import edu.java.services.TgChatService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JdbcTgChatService implements TgChatService {

    private final JdbcChatDao jdbcChatDao;

    @Override
    public void register(long tgChatId) {
        jdbcChatDao.add(tgChatId);
    }

    @Override
    public void unregister(long tgChatId) {
        jdbcChatDao.delete(tgChatId);
    }
}

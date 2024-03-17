package edu.java.scrapper;

import edu.java.domain.ChatDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

public class ChatDaoTest extends IntegrationTest{
    @Test
    @Transactional
    @Rollback
    public void chatDaoFunctionsTest() {
        var dataSource = DataSourceBuilder.create()
                .url(POSTGRES.getJdbcUrl())
                .username(POSTGRES.getUsername())
                .password(POSTGRES.getPassword())
                .build();
        var jdbcTemplate = new JdbcTemplate(dataSource);
        var chatId = 424242L;

        ChatDao.add(jdbcTemplate, chatId);
        var setAdded = ChatDao.findAll(jdbcTemplate);
        assertEquals(1, setAdded.size());
        assertEquals(chatId, setAdded.get(0));
        ChatDao.delete(jdbcTemplate, chatId);
        var setRemoved = ChatDao.findAll(jdbcTemplate);
        assertTrue(setRemoved.isEmpty());
    }
}

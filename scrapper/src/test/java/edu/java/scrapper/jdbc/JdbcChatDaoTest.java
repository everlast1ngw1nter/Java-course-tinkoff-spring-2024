package edu.java.scrapper.jdbc;

import edu.java.domain.jdbcdao.JdbcChatDao;
import edu.java.scrapper.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcChatDaoTest extends IntegrationTest {

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
        var chatDao = new JdbcChatDao(jdbcTemplate);
        var chatId = 424241L;

        chatDao.add(chatId);
        var setAdded =  chatDao.findAll();
        assertTrue(setAdded.contains(chatId));
        chatDao.delete(chatId);
        var setRemoved = chatDao.findAll();
        assertFalse(setRemoved.contains(chatId));
    }
}

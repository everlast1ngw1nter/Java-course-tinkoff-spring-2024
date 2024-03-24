package edu.java.scrapper.jdbc;

import edu.java.domain.jdbcdao.JdbcChatDao;
import edu.java.domain.jdbcdao.JdbcChatLinkDao;
import edu.java.domain.jdbcdao.JdbcLinkDao;
import edu.java.domain.LinkDto;
import edu.java.scrapper.IntegrationTest;
import java.sql.Timestamp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcLinkDaoTest extends IntegrationTest {

    @Test
    @Transactional
    @Rollback
    public void linkDaoFunctionsTest() {
        var dataSource = DataSourceBuilder.create()
                .url(POSTGRES.getJdbcUrl())
                .username(POSTGRES.getUsername())
                .password(POSTGRES.getPassword())
                .build();
        var jdbcTemplate = new JdbcTemplate(dataSource);
        var chatDao = new JdbcChatDao(jdbcTemplate);
        var linkDao = new JdbcLinkDao(jdbcTemplate);
        var chatLinkDao = new JdbcChatLinkDao(jdbcTemplate);
        var chatId = 55L;
        chatDao.add(chatId);

        var linkId = 123L;
        var url = "https://example.com";
        var lastUpdate = new Timestamp(12345);
        var lastCheckTime = new Timestamp(80000);
        var linkData = new LinkDto(linkId, url, lastUpdate, lastCheckTime, chatId);
        linkDao.add(linkData);
        chatLinkDao.update(linkId, chatId);
        var setAdded = linkDao.findAll(chatId);
        assertEquals(1, setAdded.size());
        assertEquals(linkData, setAdded.get(0));
        chatLinkDao.delete(linkId, chatId);
        var setRemoved = linkDao.findAll(chatId);
        assertTrue(setRemoved.isEmpty());
        chatDao.delete(55L);
    }
}

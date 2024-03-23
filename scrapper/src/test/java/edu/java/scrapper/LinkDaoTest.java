package edu.java.scrapper;

import edu.java.domain.ChatDao;
import edu.java.domain.LinkDao;
import edu.java.domain.LinkDto;
import java.sql.Timestamp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

public class LinkDaoTest extends IntegrationTest{

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
        var chatDao = new ChatDao(jdbcTemplate);
        var linkDao = new LinkDao(jdbcTemplate);
        var chatId = 55L;
        chatDao.add(chatId);

        var linkId = 123L;
        var url = "https://example.com";
        var lastUpdate = new Timestamp(12345);
        var lastCheckTime = new Timestamp(80000);
        var linkData = new LinkDto(linkId, url, lastUpdate, lastCheckTime, chatId);
        linkDao.add(linkData);
        var setAdded = linkDao.findAll(chatId);
        assertEquals(1, setAdded.size());
        assertEquals(linkData, setAdded.get(0));
        linkDao.delete(linkId, chatId);
        var setRemoved = linkDao.findAll(chatId);
        assertTrue(setRemoved.isEmpty());
        chatDao.delete(55L);
    }
}

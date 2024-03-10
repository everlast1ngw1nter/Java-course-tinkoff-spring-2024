package edu.java.scrapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class MigrationsIntegrationTest extends IntegrationTest {
    @Test
    public void migrationTest() {
        var dataSource = DataSourceBuilder.create()
                .url(POSTGRES.getJdbcUrl())
                .username(POSTGRES.getUsername())
                .password(POSTGRES.getPassword())
                .build();
        var jdbcTemplate = new JdbcTemplate(dataSource);
        var chatId = 424242L;
        jdbcTemplate.update("INSERT INTO scrapper.public.chat (chat_id) VALUES (?)", chatId);
        var actualChatId = jdbcTemplate.queryForObject("SELECT chat_id FROM scrapper.public.chat WHERE chat_id = ?", Long.class, chatId);
        assertThat(actualChatId).isEqualTo(chatId);
    }
}

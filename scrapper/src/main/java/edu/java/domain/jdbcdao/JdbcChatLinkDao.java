package edu.java.domain.jdbcdao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcChatLinkDao {

    private final JdbcTemplate jdbcTemplate;

    public void delete(long linkId, long chatId) {
        jdbcTemplate.update("DELETE FROM scrapper.public.chat_link "
                + "WHERE link_id = (?) AND chat_id = (?)", linkId, chatId);
    }

    public void update(long linkId, long chatId) {
        jdbcTemplate.update("INSERT INTO scrapper.public.chat_link (chat_id, link_id) VALUES (?, ?)", chatId, linkId);
    }
}
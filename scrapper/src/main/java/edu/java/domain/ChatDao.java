package edu.java.domain;


import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class ChatDao {

    private ChatDao() {
    }

    public static void add(JdbcTemplate jdbcTemplate, long chatId) {
        jdbcTemplate.update("INSERT INTO scrapper.public.chat (id) VALUES (?)", chatId);
    }

    public static void delete(JdbcTemplate jdbcTemplate, long chatId) {
        jdbcTemplate.update("DELETE FROM scrapper.public.chat WHERE id = (?)", chatId);
    }

    public static List<Long> findAll(JdbcTemplate jdbcTemplate) {
        var rowSet = jdbcTemplate.queryForRowSet("SELECT id FROM scrapper.public.chat");
        return convertToDto(rowSet);
    }

    private static List<Long> convertToDto(SqlRowSet rowSet) {
        var listDto = new ArrayList<Long>();
        while (rowSet.next()) {
            listDto.add(rowSet.getLong("id"));
        }
        return listDto;
    }
}

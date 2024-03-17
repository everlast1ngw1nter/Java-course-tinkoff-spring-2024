package edu.java.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class LinkDao {

    private LinkDao() {
    }

    private static final long FIVE_MINUTES = 5 * 60 * 1000;


    public static void add(JdbcTemplate jdbcTemplate, LinkDto linkDto) {
        jdbcTemplate.update(
                "INSERT INTO scrapper.public.link"
                        + "(id, url, last_update, last_check_time, chat_id)"
                        + "VALUES (?, ?, ?, ?, ?)",
                linkDto.id(), linkDto.url(), linkDto.lastUpdate(),
                linkDto.lastCheckTime(), linkDto.chatId());
    }

    public static void delete(JdbcTemplate jdbcTemplate, long linkId, long chatId) {
        jdbcTemplate.update("DELETE FROM scrapper.public.link WHERE id = (?) AND chat_id = (?)", linkId, chatId);
    }

    public static List<LinkDto> findAll(JdbcTemplate jdbcTemplate, long chatId) {
        var rowSet = jdbcTemplate.queryForRowSet(
                "SELECT * FROM scrapper.public.link WHERE chat_id = (?)", chatId);
        return convertToDto(rowSet);
    }

    public static List<LinkDto> findAllStaleLinks(JdbcTemplate jdbcTemplate) {
        var staleTime = new Timestamp(System.currentTimeMillis() - FIVE_MINUTES);
        var rowSet = jdbcTemplate.queryForRowSet(
                "SELECT * FROM scrapper.public.link WHERE last_check_time < ?", staleTime);
        return convertToDto(rowSet);
    }

    private static List<LinkDto> convertToDto(SqlRowSet rowSet) {
        var listDto = new ArrayList<LinkDto>();
        while (rowSet.next()) {
            listDto.add(new LinkDto(
                    rowSet.getLong("id"),
                    rowSet.getString("url"),
                    rowSet.getTimestamp("last_update"),
                    rowSet.getTimestamp("last_check_time"),
                    rowSet.getLong("chat_id")
            ));
        }
        return listDto;
    }
}


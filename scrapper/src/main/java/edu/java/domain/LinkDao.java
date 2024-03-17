package edu.java.domain;

import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class LinkDao {

    private LinkDao() {
    }


    public static void add(JdbcTemplate jdbcTemplate, LinkDto linkDto) {
        jdbcTemplate.update(
                "INSERT INTO scrapper.public.link"
                        + "(id, url, last_update, last_check_time, chat_id)"
                        + "VALUES (?, ?, ?, ?, ?)",
                linkDto.id(), linkDto.url(), linkDto.lastUpdate(),
                linkDto.lastCheckTime(), linkDto.chatId());
    }

    public static void delete(JdbcTemplate jdbcTemplate, long linkId) {
        jdbcTemplate.update("DELETE FROM scrapper.public.link WHERE id = (?)", linkId);
    }

    public static List<LinkDto> findAll(JdbcTemplate jdbcTemplate) {
        var rowSet = jdbcTemplate.queryForRowSet(
                "SELECT *"
                        + "FROM scrapper.public.link");
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


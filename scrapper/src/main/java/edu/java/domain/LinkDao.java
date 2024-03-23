package edu.java.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LinkDao {

    private final JdbcTemplate jdbcTemplate;

    private static final long LINK_REFRESH_TIME = 5 * 60 * 1000;


    public void add(LinkDto linkDto) {
        jdbcTemplate.update(
                "INSERT INTO scrapper.public.link"
                        + "(id, url, last_update, last_check_time, chat_id)"
                        + "VALUES (?, ?, ?, ?, ?)",
                linkDto.id(), linkDto.url(), linkDto.lastUpdate(),
                linkDto.lastCheckTime(), linkDto.chatId());
    }

    public void delete(long linkId, long chatId) {
        jdbcTemplate.update("DELETE FROM scrapper.public.link WHERE id = (?) AND chat_id = (?)", linkId, chatId);
    }

    public List<LinkDto> findAll(long chatId) {
        var rowSet = jdbcTemplate.queryForRowSet(
                "SELECT * FROM scrapper.public.link WHERE chat_id = (?)", chatId);
        return convertToDto(rowSet);
    }

    public List<LinkDto> findAllStaleLinks() {
        var staleTime = new Timestamp(System.currentTimeMillis() - LINK_REFRESH_TIME);
        var rowSet = jdbcTemplate.queryForRowSet(
                "SELECT * FROM scrapper.public.link WHERE last_check_time < ?", staleTime);
        return convertToDto(rowSet);
    }

    private List<LinkDto> convertToDto(SqlRowSet rowSet) {
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


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
                        + "(id, url, last_update, last_check_time)"
                        + "VALUES (?, ?, ?, ?)",
                linkDto.id(), linkDto.url(), linkDto.lastUpdate(),
                linkDto.lastCheckTime());
    }

    public List<LinkDto> findAll(long chatId) {
        var rowSet = jdbcTemplate.queryForRowSet(
                "SELECT * " +
                        "FROM chat " +
                        "JOIN chat_link ON chat.id = chat_link.chat_id " +
                        "JOIN link ON chat_link.link_id = link.id " +
                        "WHERE chat_id = (?)", chatId);
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
                    rowSet.getLong("link_id"),
                    rowSet.getString("url"),
                    rowSet.getTimestamp("last_update"),
                    rowSet.getTimestamp("last_check_time"),
                    rowSet.getLong("chat_id")
            ));
        }
        return listDto;
    }
}


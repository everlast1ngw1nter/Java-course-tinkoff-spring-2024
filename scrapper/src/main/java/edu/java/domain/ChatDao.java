package edu.java.domain;


import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class ChatDao {

    private final JdbcTemplate jdbcTemplate;

    public void add(long chatId) {
        jdbcTemplate.update("INSERT INTO scrapper.public.chat (id) VALUES (?)", chatId);
    }

    public void delete(long chatId) {
        jdbcTemplate.update("DELETE FROM scrapper.public.chat WHERE id = (?)", chatId);
    }

    public List<Long> findAll() {
        var rowSet = jdbcTemplate.queryForRowSet("SELECT id FROM scrapper.public.chat");
        return convertToDto(rowSet);
    }

    private List<Long> convertToDto(SqlRowSet rowSet) {
        var listDto = new ArrayList<Long>();
        while (rowSet.next()) {
            listDto.add(rowSet.getLong("id"));
        }
        return listDto;
    }
}

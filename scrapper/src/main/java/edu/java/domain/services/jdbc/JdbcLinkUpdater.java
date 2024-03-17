package edu.java.domain.services.jdbc;

import edu.java.domain.services.LinkUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JdbcLinkUpdater implements LinkUpdater {

    private final JdbcTemplate jdbcTemplateScrapper;
    @Override
    public int update() {
        return 0;
    }
}

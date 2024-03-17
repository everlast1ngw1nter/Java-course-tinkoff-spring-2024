package edu.java.configuration;

import edu.java.domain.services.jdbc.JdbcLinkService;
import edu.java.domain.services.jdbc.JdbcLinkUpdater;
import edu.java.domain.services.jdbc.JdbcTgChatService;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ServicesConfig {

    @Bean
    public JdbcTemplate jdbcTemplateScrapper() {
        var dataSource = DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/scrapper")
                .username("postgres")
                .password("postgres")
                .build();
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcLinkService jdbcLinkService() {
        return new JdbcLinkService(jdbcTemplateScrapper());
    }

    @Bean
    public JdbcLinkUpdater jdbcLinkUpdater() {
        return new JdbcLinkUpdater(jdbcTemplateScrapper());
    }

    @Bean
    public JdbcTgChatService jdbcTgChatService() {
        return new JdbcTgChatService(jdbcTemplateScrapper());
    }
}

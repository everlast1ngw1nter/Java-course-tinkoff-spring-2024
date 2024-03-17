package edu.java.configuration;

import edu.java.domain.services.jdbc.JdbcLinkService;
import edu.java.domain.services.jdbc.JdbcLinkUpdater;
import edu.java.domain.services.jdbc.JdbcTgChatService;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@RequiredArgsConstructor
public class ServicesConfig {

    @Bean
    public static DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/scrapper");
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
        /*
        return DataSourceBuilder.create()
        .url("jdbc:postgresql://localhost:5432/scrapper")
        .username("postgres")
        .password("postgres")
        .build();
        Factory method 'dataSource' threw exception with message: No supported DataSource type found
        это почему-то работает в тестах, но не здесь, хотя зависимости вроде все проставлены
        */
    }

    @Bean
    public JdbcTemplate jdbcTemplateScrapper() {
        return new JdbcTemplate(dataSource());
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

package edu.java.configuration.accesstype;


import edu.java.domain.jdbcdao.JdbcChatDao;
import edu.java.domain.jdbcdao.JdbcChatLinkDao;
import edu.java.domain.jdbcdao.JdbcLinkDao;
import edu.java.services.LinkService;
import edu.java.services.TgChatService;
import edu.java.services.jdbc.JdbcLinkService;
import edu.java.services.jdbc.JdbcTgChatService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
public class JdbcAccessConfiguration {

    @Bean
    public LinkService linkService(
            JdbcLinkDao jdbcLinkDao,
            JdbcChatLinkDao jdbcChatLinkDao
    ) {
        return new JdbcLinkService(jdbcLinkDao, jdbcChatLinkDao);
    }

    @Bean
    public TgChatService tgChatService(
            JdbcChatDao jdbcChatDao
    ) {
        return new JdbcTgChatService(jdbcChatDao);
    }
}

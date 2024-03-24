package edu.java.configuration.accesstype;

import edu.java.domain.jpadao.JpaChatDao;
import edu.java.domain.jpadao.JpaChatLinkDao;
import edu.java.domain.jpadao.JpaLinkDao;
import edu.java.services.LinkService;
import edu.java.services.TgChatService;
import edu.java.services.jpa.JpaLinkService;
import edu.java.services.jpa.JpaTgChatService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {

    @Bean
    public LinkService linkService(
            JpaLinkDao jpaLinkDao,
            JpaChatLinkDao jpaChatLinkDao
    ) {
        return new JpaLinkService(jpaLinkDao, jpaChatLinkDao);
    }

    @Bean
    public TgChatService tgChatService(
            JpaChatDao jpaChatDao
    ) {
        return new JpaTgChatService(jpaChatDao);
    }
}

package edu.java;

import edu.java.clients.BotWebClient;
import edu.java.clients.github.GithubWebClient;
import edu.java.clients.stackoverflow.StackOverflowWebClient;
import edu.java.domain.services.LinkService;
import edu.java.models.responses.LinkResponse;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@EnableScheduling
@ConditionalOnProperty(value = "app.scheduler.enable", havingValue = "true")
public class LinkUpdaterScheduler {

    private final GithubWebClient githubClient;

    private final StackOverflowWebClient stackOverflowClient;

    private final BotWebClient botClient;

    private final LinkService jdbcLinkService;

    private final static Logger LOGGER = LogManager.getLogger();

    @Scheduled(fixedDelayString = "#{new Integer(${app.scheduler.interval}) * 1000}")
    public void update() {
        LOGGER.info("update was called");
        var staleLinks = jdbcLinkService.listAllStale();
        for (var link : staleLinks.links()) {
            if (isUpdated(link)) {
                botClient.sendUpdates()
            }
        }
    }

    private boolean isUpdated(LinkResponse link) {
        if (link.url().getHost().equals("github.com")) {
            var parts = link.url().getPath().split("/");
            var resp = githubClient.fetchRepo(parts[0], parts[1]).block();
            return checkLessFiveMinutes(resp.updatedAt());
        } else if (link.url().getHost().equals("stackoverflow.com")) {
            var parts = link.url().getPath().split("/");
            var itemsList = stackOverflowClient.fetchQuestion(Long.parseLong(parts[1])).block();
            return itemsList.items()
                    .stream()
                    .anyMatch(item -> checkLessFiveMinutes(longToOffsetDateTime(item.lastActivityDate())));
        }
        return false;
    }

    private OffsetDateTime longToOffsetDateTime(long longTime) {
        Instant instant = Instant.ofEpochMilli(longTime);
        return OffsetDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

    private boolean checkLessFiveMinutes(OffsetDateTime lastCheckTime) {
        var currentDateTime = OffsetDateTime.now();
        long diffInMinutes = currentDateTime.until(lastCheckTime, ChronoUnit.MINUTES);
        return diffInMinutes < 5;
    }
}

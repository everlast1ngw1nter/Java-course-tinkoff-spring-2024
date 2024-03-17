package edu.java.domain.services.jdbc;

import edu.java.domain.LinkDao;
import edu.java.domain.LinkDto;
import edu.java.domain.services.LinkService;
import edu.java.models.responses.LinkResponse;
import edu.java.models.responses.ListLinksResponse;
import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class JdbcLinkService implements LinkService {

    private final JdbcTemplate jdbcTemplateScrapper;

    @Override
    public void add(long tgChatId, URI url) {
        var currentTimestamp = new Timestamp(System.currentTimeMillis());
        var linkInfo = new LinkDto(url.hashCode(), url.toString(), currentTimestamp, currentTimestamp, tgChatId);
        LinkDao.add(jdbcTemplateScrapper, linkInfo);
    }

    @Override
    public void remove(long tgChatId, URI url) {
        LinkDao.delete(jdbcTemplateScrapper, url.hashCode(), tgChatId);
    }

    @Override
    public ListLinksResponse listAll(long tgChatId) {
        var allLinksById = LinkDao.findAll(jdbcTemplateScrapper, tgChatId);
        return convertToListLinkResponse(allLinksById);
    }

    @Override
    public ListLinksResponse listAllStale() {
        var allStaleLinks = LinkDao.findAllStaleLinks(jdbcTemplateScrapper);
        return convertToListLinkResponse(allStaleLinks);
    }

    private ListLinksResponse convertToListLinkResponse(List<LinkDto> linksDto) {
        var linksResponse = linksDto
                .stream()
                .map(link -> new LinkResponse(link.id(), URI.create(link.url())))
                .toList();
        return new ListLinksResponse(linksResponse, linksResponse.size());
    }
}

package edu.java.services.jdbc;

import edu.java.domain.ChatLinkDao;
import edu.java.domain.LinkDao;
import edu.java.domain.LinkDto;
import edu.java.models.responses.LinkResponse;
import edu.java.models.responses.ListLinksResponse;
import edu.java.services.LinkService;
import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class JdbcLinkService implements LinkService {

    private final LinkDao linkDao;

    private final ChatLinkDao chatLinkDao;

    @Override
    public void add(long tgChatId, URI url) {
        var currentTimestamp = new Timestamp(System.currentTimeMillis());
        var linkInfo = new LinkDto(url.hashCode(), url.toString(), currentTimestamp, currentTimestamp, tgChatId);
        linkDao.add(linkInfo);
        chatLinkDao.update(url.hashCode(), tgChatId);
    }

    @Override
    public void remove(long tgChatId, URI url) {
        chatLinkDao.delete(url.hashCode(), tgChatId);
    }

    @Override
    public ListLinksResponse listAll(long tgChatId) {
        var allLinksById = linkDao.findAll(tgChatId);
        return convertToListLinkResponse(allLinksById);
    }

    @Override
    public ListLinksResponse listAllStale() {
        var allStaleLinks = linkDao.findAllStaleLinks();
        updateCheckTime(allStaleLinks);
        return convertToListLinkResponse(allStaleLinks);
    }

    private ListLinksResponse convertToListLinkResponse(List<LinkDto> linksDto) {
        var linksResponse = linksDto
                .stream()
                .map(link -> new LinkResponse(link.id(), URI.create(link.url()), link.chatId()))
                .toList();
        return new ListLinksResponse(linksResponse, linksResponse.size());
    }

    private void updateCheckTime(List<LinkDto> staleLinks) {
        for (var link : staleLinks) {
            linkDao.updateCheckTime(link.id());
        }
    }
}

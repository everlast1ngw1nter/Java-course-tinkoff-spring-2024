package edu.java.services.jpa;

import edu.java.domain.jpadao.JpaChatLinkDao;
import edu.java.domain.jpadao.JpaLinkDao;
import edu.java.domain.jpadao.models.Chat;
import edu.java.domain.jpadao.models.ChatLink;
import edu.java.domain.jpadao.models.Link;
import edu.java.models.responses.LinkResponse;
import edu.java.models.responses.ListLinksResponse;
import edu.java.services.LinkService;
import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaLinkService implements LinkService {

    private static final long REFRESH_LINK_TIME = 5 * 60 * 1000;

    private final JpaLinkDao jpaLinkDao;

    private final JpaChatLinkDao jpaChatLinkDao;

    @Override
    public void add(long tgChatId, URI url) {
        var link = new Link();
        var currTimestamp = new Timestamp(System.currentTimeMillis());
        link.setId((long) url.toString().hashCode());
        link.setUrl(url.toString());
        link.setLastUpdate(currTimestamp);
        link.setLastCheckTime(currTimestamp);
        jpaLinkDao.saveAndFlush(link);

        var chat = new Chat();
        chat.setId(tgChatId);

        var chatLink = new ChatLink();
        chatLink.setChat(chat);
        chatLink.setLink(link);

        jpaChatLinkDao.saveAndFlush(chatLink);
    }

    @Override
    public void remove(long tgChatId, URI url) {
        var linkId = (long) url.toString().hashCode();
        jpaLinkDao.deleteById(linkId);
        jpaChatLinkDao.deleteByLinkIdAndChatId(linkId, tgChatId);
    }

    @Override
    public ListLinksResponse listAll(long tgChatId) {
        var listLinks = jpaLinkDao.findAllByChatId(tgChatId);
        return convertLinksToLinksResponse(listLinks, tgChatId);
    }

    @Override
    public ListLinksResponse listAllStale() {
        var staleList = jpaLinkDao.findAllStaleLinks(new Timestamp(System.currentTimeMillis() - REFRESH_LINK_TIME));
        return convertStaleLinksToLinksResponse(staleList);
    }

    private ListLinksResponse convertLinksToLinksResponse(List<Link> listLinks, Long tgChatId) {
        var links = listLinks
                .stream()
                .map(elem -> new LinkResponse(
                        elem.getId(),
                        URI.create(elem.getUrl()),
                        tgChatId
                ))
                .toList();
        return new ListLinksResponse(links, links.size());
    }

    private ListLinksResponse convertStaleLinksToLinksResponse(List<Object[]> listStaleLinks) {
        var links = listStaleLinks
                .stream()
                .map(staleLink -> (new LinkResponse(
                        ((Link) staleLink[0]).getId(),
                        URI.create(((Link) staleLink[0]).getUrl()),
                        ((Chat) staleLink[1]).getId())))
                .toList();
        return new ListLinksResponse(links, links.size());
    }
}

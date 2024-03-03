package edu.java.mock;

import edu.java.models.exceptions.AlreadyExistException;
import edu.java.models.exceptions.NotExistException;
import edu.java.models.responses.LinkResponse;
import edu.java.models.responses.ListLinksResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;

public class FakeDb {
    private FakeDb() {
    }

    private static final HashSet<URI> USER_LINKS = new HashSet<>();
    private static final HashSet<Long> USER_IDS = new HashSet<>();

    public static void registerChat(long id) {
        if (USER_IDS.contains(id)) {
            throw new AlreadyExistException("Already exists chat with id = " + id, "Cannot register chat");
        }
        USER_IDS.add(id);
    }

    public static void deleteChat(long id) {
        if (!USER_IDS.contains(id)) {
            throw new NotExistException("There is no chat with id = " + id, "Cannot delete chat");
        }
        USER_IDS.remove(id);
    }

    public static ListLinksResponse getLinks(int tgChatId) {
        return new ListLinksResponse(new ArrayList<>(), -1);
    }

    public static LinkResponse addLink(int tgChatId, URI link) {
        if (USER_LINKS.contains(link)) {
            throw new AlreadyExistException("Already exists link = " + link, "Cannot add link");
        }
        USER_LINKS.add(link);
        return new LinkResponse(tgChatId, link);
    }

    public static LinkResponse removeLink(int tgChatId, URI link) {
        if (!USER_LINKS.contains(link)) {
            throw new NotExistException("There is no link " + link, "Cannot delete link");
        }
        USER_LINKS.remove(link);
        return new LinkResponse(tgChatId, link);
    }
}

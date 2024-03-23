package edu.java.services;

import edu.java.models.responses.ListLinksResponse;
import java.net.URI;

public interface LinkService {
    void add(long tgChatId, URI url);

    void remove(long tgChatId, URI url);

    ListLinksResponse listAll(long tgChatId);

    ListLinksResponse listAllStale();
}


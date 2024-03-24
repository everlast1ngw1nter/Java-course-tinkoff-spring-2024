package edu.java.models.requests;

import java.net.URI;

public record LinkUpdateRequest(long id, URI url, String description, Long tgChatIds) {}

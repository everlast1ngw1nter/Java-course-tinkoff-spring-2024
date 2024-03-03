package edu.java.models.requests;

import java.util.List;

public record LinkUpdateRequest(int id, String url, String description, List<Long> tgChatIds) {}
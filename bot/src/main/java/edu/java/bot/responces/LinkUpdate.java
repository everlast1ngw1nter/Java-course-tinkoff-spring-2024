package edu.java.bot.responces;

import java.util.List;

public record LinkUpdate (int id, String url, String description, List<Long> tgChatIds) {}
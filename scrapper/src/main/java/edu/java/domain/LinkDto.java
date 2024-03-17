package edu.java.domain;

import java.sql.Timestamp;

public record LinkDto(long id, String url, Timestamp lastUpdate,
                      Timestamp lastCheckTime, long chatId) {
}

package edu.java.clients.stackoverflow;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Item (
    Owner owner,
    @JsonProperty("is_answered")
    boolean isAnswered,
    @JsonProperty("answer_count")
    int answerCount,
    @JsonProperty("last_activity_date")
    long lastActivityDate) { }

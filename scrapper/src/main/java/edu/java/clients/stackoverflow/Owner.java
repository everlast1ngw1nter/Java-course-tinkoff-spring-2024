package edu.java.clients.stackoverflow;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Owner(
        @JsonProperty("account_id")
        Long accountId,
        String link) { }

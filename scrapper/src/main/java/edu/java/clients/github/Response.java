package edu.java.clients.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public record Response(
        long id,
        String name,
        @JsonProperty("html_url")
        String url,
        @JsonProperty("updated_at")
        OffsetDateTime updatedAt,
        Owner owner) { }

package edu.java.models.exceptions;

import edu.java.models.responses.ApiErrorResponse;

public class NotExistException extends RuntimeException {
    public final String description;

    public NotExistException(String description, String message) {
        super(message);
        this.description = description;
    }
}

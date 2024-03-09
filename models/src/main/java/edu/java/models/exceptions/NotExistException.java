package edu.java.models.exceptions;

public class NotExistException extends RuntimeException {
    public final String description;

    public NotExistException(String description, String message) {
        super(message);
        this.description = description;
    }
}

package edu.java.models.exceptions;


public class AlreadyExistException extends RuntimeException {
    public final String description;

    public AlreadyExistException(String description, String message) {
        super(message);
        this.description = description;
    }
}

package edu.java.exception;

import edu.java.models.exceptions.AlreadyExistException;
import edu.java.models.exceptions.NotExistException;
import edu.java.models.responses.ApiErrorResponse;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistException.class)
    public ApiErrorResponse handleAlreadyExistException(AlreadyExistException exception) {
        return new ApiErrorResponse(
                exception.description,
                HttpStatus.CONFLICT.toString(),
                exception.getClass().getName(),
                exception.getMessage(),
                convertStackTraceToList(exception.getStackTrace())
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotExistException.class)
    public ApiErrorResponse handleNotExistException(NotExistException exception) {
        return new ApiErrorResponse(
                exception.description,
                HttpStatus.NOT_FOUND.toString(),
                exception.getClass().getName(),
                exception.getMessage(),
                convertStackTraceToList(exception.getStackTrace())
        );
    }

    private static List<String> convertStackTraceToList(StackTraceElement[] stackTrace) {
        return Arrays.stream(stackTrace)
                .map(StackTraceElement::toString)
                .toList();
    }
}

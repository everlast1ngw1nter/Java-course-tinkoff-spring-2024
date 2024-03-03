package edu.java;

import edu.java.models.exceptions.AlreadyExistException;
import edu.java.models.exceptions.NotExistException;
import edu.java.models.responses.ApiErrorResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {

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
        var stackTraceList = new ArrayList<String>();
        for (var elem : stackTrace) {
            stackTraceList.add(elem.toString());
        }
        return stackTraceList;
    }
}

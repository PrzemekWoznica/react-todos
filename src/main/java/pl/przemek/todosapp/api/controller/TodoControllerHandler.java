package pl.przemek.todosapp.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.przemek.todosapp.api.utils.NoContentException;
import pl.przemek.todosapp.api.utils.TodoNotFoundException;

@RestControllerAdvice
public class TodoControllerHandler {
    @ExceptionHandler(TodoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String todoNotFoundHandler(TodoNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String noContentHandler(NoContentException exception) {
        return exception.getMessage();
    }
}

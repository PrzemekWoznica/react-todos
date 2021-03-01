package pl.przemek.todosapp.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.przemek.todosapp.api.utils.UserAlreadyExistsException;

@RestControllerAdvice
public class UserControllerHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String userControllerHandler(UserAlreadyExistsException exception) {
        return exception.getMessage();
    }
}

package pl.przemek.todosapp.api.controller;

import org.springframework.web.bind.annotation.*;
import pl.przemek.todosapp.api.dto.AuthenticationDTO;
import pl.przemek.todosapp.api.model.User;
import pl.przemek.todosapp.api.repository.UserRepository;
import pl.przemek.todosapp.api.service.UserService;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public AuthenticationDTO doesUserExist(@RequestBody User user) {
        return userService.loginAuthentication(user);
    }

    @PostMapping("/signup")
    public AuthenticationDTO newUser(@RequestBody User user) {
        return userService.signUpAuthentication(user);
    }
}

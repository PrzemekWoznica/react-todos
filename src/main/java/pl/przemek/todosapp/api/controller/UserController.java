package pl.przemek.todosapp.api.controller;

import org.springframework.web.bind.annotation.*;
import pl.przemek.todosapp.api.dto.AuthenticationDTO;
import pl.przemek.todosapp.api.model.User;
import pl.przemek.todosapp.api.repository.UserRepository;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public AuthenticationDTO doesUserExist(@RequestBody User user) {
        if ( user == null || user.getUsername().equals("")) {
            return new AuthenticationDTO(false);
        }
        Optional<User> databaseUser = userRepository.findByUsername(user.getUsername());

        return new AuthenticationDTO(databaseUser.isPresent());
    }

    @PostMapping("/signup")
    public AuthenticationDTO newUser(@RequestBody User user) {
        if (user.getUsername().equals("") || userRepository.findByUsername(user.getUsername()).isPresent()) {
            return new AuthenticationDTO(false);
        } else {
            User newUser = new User(user.getUsername());
            userRepository.save(newUser);
            return new AuthenticationDTO(true);
        }
    }
}

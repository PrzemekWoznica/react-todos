package pl.przemek.todosapp.api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import pl.przemek.todosapp.api.dto.AuthenticationDTO;
import pl.przemek.todosapp.api.model.User;
import pl.przemek.todosapp.api.repository.UserRepository;
import pl.przemek.todosapp.api.utils.UserAlreadyExistsException;

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
        if ( user == null || user.getUsername() == "") {
            return new AuthenticationDTO(false);
        }
        Optional<User> databaseUser = userRepository.findByUsername(user.getUsername());

        return new AuthenticationDTO(databaseUser.isPresent());
    }

    @PostMapping("/signup")
    public AuthenticationDTO newUser(@RequestBody User user) throws UserAlreadyExistsException {
        if (user.getUsername() == "") {
            return new AuthenticationDTO(false);
        } else if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User with username: " + user.getUsername() + " already exists");
        } else {
            User newUser = new User(user.getUsername());
            userRepository.save(newUser);
            return new AuthenticationDTO(true);
        }
    }
}

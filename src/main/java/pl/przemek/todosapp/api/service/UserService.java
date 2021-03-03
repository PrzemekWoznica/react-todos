package pl.przemek.todosapp.api.service;

import org.springframework.stereotype.Service;
import pl.przemek.todosapp.api.dto.AuthenticationDTO;
import pl.przemek.todosapp.api.model.User;
import pl.przemek.todosapp.api.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthenticationDTO loginAuthentication(User user) {
        if ( user == null || user.getUsername().equals("")) {
            return new AuthenticationDTO(false);
        } else if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return new AuthenticationDTO(true);
        } else {
            return new AuthenticationDTO(false);
        }
    }

    public AuthenticationDTO signUpAuthentication(User user) {
        if (user.getUsername().equals("") || userRepository.findByUsername(user.getUsername()).isPresent()) {
            return new AuthenticationDTO(false);
        } else {
            User newUser = new User(user.getUsername());
            userRepository.save(newUser);
            return new AuthenticationDTO(true);
        }
    }
}

package pl.przemek.todosapp.api.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.przemek.todosapp.api.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    void shouldFindUserByUsername() {
        User testUser = new User("Test user");
        userRepository.save(testUser);

        assertThat(userRepository.findByUsername("Test user")).isPresent();
    }

    @Test
    void shouldSaveUser() {
        User testUser = new User("Test user");

        assertThat(userRepository.save(testUser))
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(testUser);
    }
}
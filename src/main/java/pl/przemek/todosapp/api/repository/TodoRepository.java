package pl.przemek.todosapp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import pl.przemek.todosapp.api.model.Todo;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findByIdAndUser(Long id, String user);
    List<Todo> findByUser(String user);
}

package pl.przemek.todosapp.api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.przemek.todosapp.api.model.Todo;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TodoRepositoryTests {

    @Autowired
    TodoRepository todoRepository;

    @Test
    void shouldFindOptionalTodoByIdAndUser() {
        Todo testTodo = new Todo(null, "Test content", false, "Test user");

        Todo savedTodo = todoRepository.save(testTodo);

        assertThat(todoRepository.findByIdAndUser(savedTodo.getId(), "Test user")).isPresent();
    }

    @Test
    void shouldFindTodoListByUser() {
        Todo testTodo = new Todo("Test content", false, "Test user");

        todoRepository.save(testTodo);

        assertThat(todoRepository.findByUser("Test user")).isNotEmpty();
        assertThat(todoRepository.findByUser("Test user")).isInstanceOf(List.class);
    }

    @Test
    void shouldReturnTodoById() {
        Todo testTodo = new Todo("test content", false, "test user");

        Todo savedTodo = todoRepository.save(testTodo);

        assertThat(todoRepository.findById(savedTodo.getId())).isPresent();
    }

    @Test
    void shouldSaveAndReturnTodo() {
        Todo testTodo = new Todo(null, "Test content", false, "Test user");

        Todo savedTodo = todoRepository.save(testTodo);

        assertThat(savedTodo).usingRecursiveComparison().ignoringFields("id").isEqualTo(testTodo);
    }
}
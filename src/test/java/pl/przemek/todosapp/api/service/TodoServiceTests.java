package pl.przemek.todosapp.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.przemek.todosapp.api.model.Todo;
import pl.przemek.todosapp.api.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class TodoServiceTests {
    @Autowired
    TodoService todoService;

    @MockBean
    TodoRepository todoRepository;

    @Test
    void shouldReturnList() {
        Todo testTodo = new Todo("test content", false, "test user");
        List<Todo> testList = new ArrayList<>();
        testList.add(testTodo);
        when(todoRepository.findByUser(anyString())).thenReturn(testList);

        assertThat(todoService.showTodos("test")).isNotEmpty();
        assertThat(todoService.showTodos("test")).isInstanceOf(List.class);
        verify(todoRepository, times(2)).findByUser(any());
    }

    @Test
    void shouldAddAndReturnTodo() {
        Todo testTodo = new Todo("test content", false, "test user");
        testTodo.setId(1L);

        when(todoRepository.save(any())).thenReturn(testTodo);

        assertThat(todoService.addTodo(new Todo(), "test")).isInstanceOf(Todo.class);
        verify(todoRepository, times(1)).save(any());
    }

    @Test
    void changeTodo() {

    }

    @Test
    void removeTodos() {

    }

    @Test
    void removeTodo() {
    }
}
package pl.przemek.todosapp.api.repository;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.przemek.todosapp.api.model.Todo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest
class TodoRepositoryTests {

    private final static Long TODO_ID = 1L;
    private final static String USER = "USER";

    @Mock
    TodoRepository todoRepository;

    @Captor
    ArgumentCaptor<Todo> captor;

    @Test
    void shouldReturnOptionalTodoByIdAndUser() {
        //given
        Todo testTodo = createTodo();
        when(todoRepository.findByIdAndUser(TODO_ID, USER)).thenReturn(Optional.of(testTodo));

        //when
        Optional<Todo> resultTodo = todoRepository.findByIdAndUser(TODO_ID, USER);

        //then
        verify(todoRepository, times(1)).findByIdAndUser(TODO_ID, USER);
        assertThat(resultTodo.get()).isEqualTo(testTodo);
    }

    @Test
    void shouldReturnEmptyOptionalIfWrongIdOrUser() {
        //given
        when(todoRepository.findByIdAndUser(anyLong(), anyString())).thenReturn(Optional.empty());

        //when
        Optional<Todo> resultTodo = todoRepository.findByIdAndUser(2L, "WRONG_USER");

        //then
        verify(todoRepository, times(1)).findByIdAndUser(anyLong(), anyString());
        assertThat(resultTodo).isEmpty();
    }

    @Test
    void shouldReturnTodoListByUser() {
        //given
        Todo testTodo = createTodo();
        List<Todo> list = new ArrayList<>();
        list.add(testTodo);
        when(todoRepository.findByUser(USER)).thenReturn(list);

        //when
        List<Todo> resultList = todoRepository.findByUser(USER);

        //then
        verify(todoRepository, times(1)).findByUser(USER);
        assertThat(resultList).isEqualTo(list);
    }

    @Test
    void shouldReturnEmptyListIfUserNotExists() {
        //given
        when(todoRepository.findByUser(anyString())).thenReturn(Collections.emptyList());

        //when
        List<Todo> resultList = todoRepository.findByUser("WRONG_USER");

        //then
        verify(todoRepository, times(1)).findByUser(anyString());
        assertThat(resultList).isEmpty();
    }

    @Test
    void shouldReturnTodoById() {
        //given
        Todo testTodo = createTodo();
        when(todoRepository.findById(TODO_ID)).thenReturn(Optional.of(testTodo));

        //when
        Todo resultTodo = todoRepository.findById(TODO_ID).get();

        //then
        verify(todoRepository, times(1)).findById(TODO_ID);
        assertThat(resultTodo).isEqualTo(testTodo);
    }

    @Test
    void shouldNotReturnTodoIfWrongId() {
        //given
        when(todoRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when
        Optional<Todo> resultTodo = todoRepository.findById(2L);

        //then
        verify(todoRepository, times(1)).findById(anyLong());
        assertThat(resultTodo).isEmpty();
    }

    @Test
    void shouldSaveAndReturnTodo() {
        //given
        Todo testTodo = createTodo();
        when(todoRepository.save(testTodo)).thenReturn(testTodo);

        //when
        Todo resultTodo = todoRepository.save(testTodo);

        //then
        verify(todoRepository, times(1)).save(any());

        assertThat(resultTodo).isEqualTo(testTodo);
    }

    @Test
    void shouldDeleteAllTodosByUser() {
        //given
        Todo testTodo = createTodo();
        todoRepository.save(testTodo);

        //when
        todoRepository.deleteByUser(USER);

        //then
        verify(todoRepository, times(1)).deleteByUser(USER);

        assertThat(todoRepository.findByUser(USER)).isEmpty();
    }

    @Test
    void shouldDeleteTodo() {
        //given
        Todo testTodo = createTodo();
        todoRepository.save(testTodo);

        //when
        todoRepository.delete(testTodo);

        //then
        verify(todoRepository, times(1)).delete(any());

        assertThat(todoRepository.findByIdAndUser(TODO_ID, USER)).isEmpty();
    }

    private Todo createTodo() {
        return new Todo(TODO_ID, null, false, USER);
    }
}
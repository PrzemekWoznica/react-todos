package pl.przemek.todosapp.api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.przemek.todosapp.api.model.Todo;
import pl.przemek.todosapp.api.repository.TodoRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTests {
    @InjectMocks
    TodoService todoService;

    @Mock
    TodoRepository todoRepository;

    @Captor
    ArgumentCaptor<Todo> captor;

    private final static Long TODO_ID = 1L;
    private final static Long WRONG_TODO_ID = 2L;
    private final static String USER = "USER";
    private final static String NOT_USER = "NOT_USER";

    @Test
    void shouldReturnList() {
        //given
        Todo testTodo = createTodo();
        List<Todo> testList = new ArrayList<>();
        testList.add(testTodo);
        when(todoRepository.findByUser(anyString())).thenReturn(testList);

        //when
        List<Todo> resultList = todoService.showTodos(USER);

        //then
        verify(todoRepository, times(1)).findByUser(USER);
        assertThat(resultList.size()).isEqualTo(1);
    }

    @Test
    void shouldReturnEmptyList() {
        //given
        when(todoRepository.findByUser(anyString())).thenReturn(Collections.emptyList());

        //when
        List<Todo> resultList = todoService.showTodos(NOT_USER);

        //then
        verify(todoRepository, times(1)).findByUser(NOT_USER);
        assertThat(resultList).isEmpty();
    }

    @Test
    void shouldAddAndReturnTodo() {
        //given
        Todo testTodo = createTodo();
        when(todoRepository.save(any())).thenReturn(testTodo);

        //when
        Todo resultTodo = todoService.addTodo(testTodo, USER);

        //then
        verify(todoRepository, times(1)).save(any());
        assertThat(resultTodo).isEqualTo(testTodo);
    }

    @Test
    void shouldChangeTodo() {
        //given
        when(todoRepository.findByIdAndUser(anyLong(), anyString())).thenReturn(Optional.of(createTodo()));
        when(todoRepository.findById(anyLong())).thenReturn(Optional.of(createTodo()));

        //when
        todoService.changeTodo(TODO_ID, USER);

        //then
        verify(todoRepository, times(1)).findByIdAndUser(TODO_ID, USER);
        verify(todoRepository, times(1)).save(captor.capture());
        verify(todoRepository, times(1)).findById(TODO_ID);

        assertThat(captor.getValue().getChecked()).isTrue();
    }

    @Test
    void shouldNotChangeTodoIfTodoNotFound() {
        //given
        when(todoRepository.findByIdAndUser(anyLong(), anyString())).thenReturn(Optional.empty());

        //when
        todoService.changeTodo(WRONG_TODO_ID, USER);

        //then
        verify(todoRepository, times(1)).findByIdAndUser(WRONG_TODO_ID, USER);
        verify(todoRepository, times(1)).findById(WRONG_TODO_ID);
        verifyNoMoreInteractions(todoRepository);
    }

    @Test
    void shouldRemoveTodos() {
        //given
        todoRepository.save(createTodo());
        todoRepository.save(createTodo());

        //when
        todoService.removeTodos(USER);

        //then
        verify(todoRepository, times(1)).deleteByUser(USER);

        assertThat(todoRepository.findByUser(USER)).isEmpty();
    }

    @Test
    void shouldRemoveTodo() {
        //given
        Todo testTodo = createTodo();
        when(todoRepository.findByIdAndUser(anyLong(), anyString())).thenReturn(Optional.of(testTodo));

        //when
        todoService.removeTodo(TODO_ID, USER);

        //then
        verify(todoRepository, times(1)).findByIdAndUser(TODO_ID, USER);
        verify(todoRepository, times(1)).delete(testTodo);
        verifyNoMoreInteractions(todoRepository);

    }

    @Test
    void shouldNotRemoveTodoIfTodoNotFound() {
        //given
        when(todoRepository.findByIdAndUser(anyLong(), anyString())).thenReturn(Optional.empty());

        //when
        todoService.removeTodo(WRONG_TODO_ID, USER);

        //then
        verify(todoRepository, times(1)).findByIdAndUser(WRONG_TODO_ID, USER);
        verifyNoMoreInteractions(todoRepository);

    }

    private Todo createTodo() {
        return new Todo(TODO_ID, null, false, USER);
    }
}
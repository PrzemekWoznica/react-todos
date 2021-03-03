package pl.przemek.todosapp.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.przemek.todosapp.api.model.Todo;
import pl.przemek.todosapp.api.service.TodoService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
public class TodosControllerTests {
    private final static Long TODO_ID = 1L;
    private final static String USER = "user";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TodoService todoService;

    @Test
    void getMappingMethodShouldRespondWithListAsJson() throws Exception {
        //given
        List<Todo> testList = new ArrayList<>();
        testList.add(createTodo());
        when(todoService.showTodos(USER)).thenReturn(testList);

        //when
        mockMvc.perform(get("/api/" + USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        //then
        verify(todoService, times(1)).showTodos(USER);
        verifyNoMoreInteractions(todoService);
    }

    @Test
    void getMappingMethodShouldRespondWithEmptyJSON() throws Exception {
        //given
        when(todoService.showTodos(anyString())).thenReturn(Collections.emptyList());

        //when
        mockMvc.perform(get("/api/" + USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        //then
        verify(todoService, times(1)).showTodos(anyString());
        verifyNoMoreInteractions(todoService);
    }

    @Test
    void postMappingMethodShouldRespondWithJSON() throws Exception {
        //given
        when(todoService.addTodo(any(), anyString())).thenReturn(createTodo());

        //when
        mockMvc.perform(post("/api/" + USER)
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        //then
        verify(todoService, times(1)).addTodo(any(), anyString());
        verifyNoMoreInteractions(todoService);
    }

    @Test
    void putMappingMethodShouldRespondWithJSON() throws Exception {
        //given
        when(todoService.changeTodo(TODO_ID, USER)).thenReturn(Optional.of(createTodo()));

        //when
        mockMvc.perform(put("/api/" + USER + "/todo/" + TODO_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        //then
        verify(todoService, times(1)).changeTodo(TODO_ID, USER);
        verifyNoMoreInteractions(todoService);
    }

    @Test
    void deleteMappingMethodShouldRemoveAllTodos() throws Exception {
        //when
        mockMvc.perform(delete("/api/" + USER))
                .andDo(print())
                .andExpect(status().isOk());

        //then
        verify(todoService, times(1)).removeTodos(USER);
    }

    @Test
    void deleteMappingMethodShouldRemoveTodo() throws Exception {
        //when
        mockMvc.perform(delete("/api/" + USER + "/todo/" + TODO_ID))
                .andDo(print())
                .andExpect(status().isOk());

        //then
        verify(todoService, times(1)).removeTodo(TODO_ID, USER);
    }

    private Todo createTodo() {
        return new Todo(TODO_ID, null, false, USER);
    }
}

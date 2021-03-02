package pl.przemek.todosapp.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.przemek.todosapp.api.model.Todo;
import pl.przemek.todosapp.api.repository.TodoRepository;
import pl.przemek.todosapp.api.service.TodoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/{user}")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> allTodos(@PathVariable String user) {
        return todoService.showTodos(user);
    }

    @PostMapping
    public ResponseEntity<?> newTodo(@RequestBody Todo todo, @PathVariable String user) {
            return new ResponseEntity<>(todoService.addTodo(todo, user), HttpStatus.CREATED);
    }

    @PutMapping("/todo/{id}")
    public Optional<Todo> editTodo(@PathVariable Long id, @PathVariable String user) {
        return todoService.changeTodo(id, user);
    }

    @DeleteMapping
    public void byeTodos(@PathVariable String user) {
        todoService.removeTodos(user);
    }

    @DeleteMapping("/todo/{id}")
    public void byeTodo(@PathVariable Long id, @PathVariable String user) {
        todoService.removeTodo(id, user);
    }
}

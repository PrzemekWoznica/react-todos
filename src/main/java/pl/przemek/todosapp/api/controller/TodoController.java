package pl.przemek.todosapp.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.przemek.todosapp.api.model.Todo;
import pl.przemek.todosapp.api.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/{user}")
public class TodoController {
    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public List<Todo> allTodos(@PathVariable String user) {
        return todoRepository.findByUser(user);
    }

    @PostMapping
    public ResponseEntity<?> newTodo(@RequestBody Todo todo, @PathVariable String user) {
            Todo newTodo = new Todo(todo.getContent(), false, user);
            Todo newTodo1 = todoRepository.save(newTodo);
            return new ResponseEntity<>(newTodo1, HttpStatus.CREATED);
    }
    @PutMapping("/todo/{id}")
    public Optional<Todo> editTodo(@PathVariable Long id, @PathVariable String user) {
        Optional<Todo> oldTodo = todoRepository.findByIdAndUser(id, user);
        oldTodo.ifPresent(todo1 -> {
            todo1.setChecked(!todo1.getChecked());
            todoRepository.save(todo1);
        });
        return todoRepository.findById(id);
    }

    @DeleteMapping
    public void byeTodos(@PathVariable String user) {
        todoRepository.findByUser(user)
                .forEach(todo -> {
                    todoRepository.deleteById(todo.getId());
                });
    }

    @DeleteMapping("/todo/{id}")
    public void byeTodo(@PathVariable Long id, @PathVariable String user) {
        Optional<Todo> todo = todoRepository.findByIdAndUser(id, user);
        todo.ifPresent(todo1 -> todoRepository.delete(todo1));
    }
}

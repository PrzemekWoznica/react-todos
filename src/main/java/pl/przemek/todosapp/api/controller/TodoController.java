package pl.przemek.todosapp.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import pl.przemek.todosapp.api.model.Todo;
import pl.przemek.todosapp.api.repository.TodoRepository;
import pl.przemek.todosapp.api.utils.NoContentException;
import pl.przemek.todosapp.api.utils.TodoNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/{user}")
@CrossOrigin(origins = "*")
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
    public ResponseEntity<?> newTodo(@RequestBody Todo todo, @PathVariable String user) throws NoContentException {
        if (todo.getContent() != null && todo.getContent() != "") {
            Todo newTodo = new Todo(todo.getContent(), false, user);
            Todo newTodo1 = todoRepository.save(newTodo);
            return new ResponseEntity<>(newTodo1, HttpStatus.CREATED);
        } else {
            throw new NoContentException("No content");
        }
    }
    @PutMapping("/todo/{id}")
    public Optional<Todo> editTodo(@PathVariable Long id, @PathVariable String user) throws TodoNotFoundException {
        Optional<Todo> oldTodo = todoRepository.findByIdAndUser(id, user);
        oldTodo.ifPresent(todo1 -> {
            todo1.setChecked(!todo1.getChecked());
            todoRepository.save(todo1);
        });
        oldTodo.orElseThrow(() -> {
            throw new TodoNotFoundException("Not found todo with id: " + id);
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
    public void byeTodo(@PathVariable Long id, @PathVariable String user) throws TodoNotFoundException{
        Optional<Todo> todo = todoRepository.findByIdAndUser(id, user);
        todo.ifPresent(todo1 -> todoRepository.delete(todo1));
        todo.orElseThrow(() -> {
            throw new TodoNotFoundException("Not found todo with id: " + id);
        });

    }
}

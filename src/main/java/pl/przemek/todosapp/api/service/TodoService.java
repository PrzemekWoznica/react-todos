package pl.przemek.todosapp.api.service;

import org.springframework.stereotype.Service;
import pl.przemek.todosapp.api.model.Todo;
import pl.przemek.todosapp.api.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> showTodos(String user) {
        return todoRepository.findByUser(user);
    }

    public Todo addTodo(Todo todo, String user) {
        Todo newTodo = new Todo(todo.getContent(), false, user);
        return todoRepository.save(newTodo);
    }

    public Optional<Todo> changeTodo(Long id, String user) {
        Optional<Todo> oldTodo = todoRepository.findByIdAndUser(id, user);
        oldTodo.ifPresent(todo -> {
            todo.setChecked(!todo.getChecked());
            todoRepository.save(todo);
        });
        return todoRepository.findById(id);
    }

    public void removeTodos(String user) {
        todoRepository.deleteByUser(user);
    }

    public void removeTodo(Long id, String user) {
        todoRepository.findByIdAndUser(id, user).ifPresent(todo -> todoRepository.delete(todo));
    }
}

package pl.przemek.todosapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.przemek.todosapp.api.model.Todo;
import pl.przemek.todosapp.api.repository.TodoRepository;

@SpringBootApplication
public class TodosApplication
//		implements CommandLineRunner
{
//	@Autowired
//	TodoRepository todoRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//		todoRepository.save(new Todo("Test Todo", true, "przemek-woznica"));
//	}

	public static void main(String[] args) {
		SpringApplication.run(TodosApplication.class, args);

	}
}

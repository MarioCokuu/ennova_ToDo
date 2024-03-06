package com.example.mario.ennova_ToDo.config;


import com.example.mario.ennova_ToDo.entity.TodoItem;
import com.example.mario.ennova_ToDo.repository.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TodoItemDataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(TodoItemDataLoader.class);

    @Autowired
    TodoItemRepository todoItemRepository;

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    private void loadSeedData() {
        if (todoItemRepository.count() == 0) {
            TodoItem todoItem1 = new TodoItem("Ennova ToDo");

            todoItemRepository.save(todoItem1);
        }

        logger.info("Number of TodoItems: {}", todoItemRepository.count());
    }
}

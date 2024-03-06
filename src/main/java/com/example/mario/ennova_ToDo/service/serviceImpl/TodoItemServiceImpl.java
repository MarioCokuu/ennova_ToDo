package com.example.mario.ennova_ToDo.service.serviceImpl;

import com.example.mario.ennova_ToDo.entity.TodoItem;
import com.example.mario.ennova_ToDo.repository.TodoItemRepository;
import com.example.mario.ennova_ToDo.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TodoItemServiceImpl implements TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Override
    public void create(TodoItem tdi) {
        tdi.setCreatedDate(Instant.now());
        tdi.setModifiedDate(Instant.now());
        todoItemRepository.save(tdi);
    }

    @Override
    public void update(long id, TodoItem todoItem) {
        if (!todoItemRepository.existsById(id)) {
            throw new IllegalArgumentException("TodoItem with id " + id + " not found");
        }

        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);
    }
}

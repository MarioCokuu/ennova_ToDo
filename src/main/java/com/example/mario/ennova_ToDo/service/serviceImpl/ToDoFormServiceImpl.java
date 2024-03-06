package com.example.mario.ennova_ToDo.service.serviceImpl;

import com.example.mario.ennova_ToDo.entity.TodoItem;
import com.example.mario.ennova_ToDo.repository.TodoItemRepository;
import com.example.mario.ennova_ToDo.service.TodoFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoFormServiceImpl implements TodoFormService {
    @Autowired
    private TodoItemRepository todoItemRepository;
    @Override
    public TodoItem findById(Long id) {

        TodoItem todoItem = todoItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        return todoItem;
    }

    @Override
    public TodoItem delete(Long id) {
        TodoItem todoItem = todoItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        todoItemRepository.delete(todoItem);
        return todoItem;
    }
}

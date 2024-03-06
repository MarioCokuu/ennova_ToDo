package com.example.mario.ennova_ToDo.service;

import com.example.mario.ennova_ToDo.entity.TodoItem;

public interface TodoFormService {

    TodoItem findById(Long id);
    TodoItem delete(Long id);
}

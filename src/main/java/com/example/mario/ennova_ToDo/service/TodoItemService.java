package com.example.mario.ennova_ToDo.service;

import com.example.mario.ennova_ToDo.entity.TodoItem;

public interface TodoItemService {
    void create(TodoItem tdi);
    void update(long id, TodoItem todoItem);
}

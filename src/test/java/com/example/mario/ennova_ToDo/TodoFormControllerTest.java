package com.example.mario.ennova_ToDo;


import com.example.mario.ennova_ToDo.controller.TodoFormController;
import com.example.mario.ennova_ToDo.entity.TodoItem;
import com.example.mario.ennova_ToDo.service.serviceImpl.ToDoFormServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TodoFormControllerTest {

    @Mock
    private ToDoFormServiceImpl todoFormService;

    @InjectMocks
    private TodoFormController todoFormController;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testShowCreateForm() {
        String result = todoFormController.showCreateForm(new TodoItem());
        assertEquals("add-todo-item", result);
    }

    @Test
    void testShowUpdateForm() {
        long todoId = 1L;
        TodoItem mockTodoItem = new TodoItem();
        when(todoFormService.findById(todoId)).thenReturn(mockTodoItem);

        String result = todoFormController.showUpdateForm(todoId, model);

        assertEquals("update-todo-item", result);
        verify(model, times(1)).addAttribute(eq("todo"), eq(mockTodoItem));
    }

    @Test
    void testDeleteTodoItem() {
        long todoId = 1L;
        TodoItem mockTodoItem = new TodoItem();
        when(todoFormService.delete(todoId)).thenReturn(mockTodoItem);

        String result = todoFormController.deleteTodoItem(todoId, model);

        assertEquals("redirect:/", result);
        verify(todoFormService, times(1)).delete(todoId);
    }
}




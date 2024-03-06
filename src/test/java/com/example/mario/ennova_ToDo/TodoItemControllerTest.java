package com.example.mario.ennova_ToDo;


import com.example.mario.ennova_ToDo.controller.TodoItemController;
import com.example.mario.ennova_ToDo.entity.TodoItem;
import com.example.mario.ennova_ToDo.repository.TodoItemRepository;
import com.example.mario.ennova_ToDo.service.TodoItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TodoItemControllerTest {

    @Mock
    private TodoItemRepository todoItemRepository;

    @Mock
    private TodoItemService todoItemService;

    @InjectMocks
    private TodoItemController todoItemController;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testIndex() {
        List<TodoItem> todoItemList = new ArrayList<>();
        when(todoItemRepository.findAll()).thenReturn(todoItemList);

        ModelAndView modelAndView = todoItemController.index();

        assertEquals("index", modelAndView.getViewName());
        assertEquals(todoItemList, modelAndView.getModel().get("todoItems"));
    }

    @Test
    void testCreateTodoItemWithValidationError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = todoItemController.createTodoItem(new TodoItem(), bindingResult, model);

        assertEquals("add-todo-item", result);
    }

    @Test
    void testCreateTodoItemWithoutValidationError() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String result = todoItemController.createTodoItem(new TodoItem(), bindingResult, model);

        assertEquals("redirect:/", result);
        verify(todoItemService, times(1)).create(any(TodoItem.class));
    }



    @Test
    void testUpdateTodoItemWithValidationError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = todoItemController.updateTodoItem(1L, new TodoItem(), bindingResult, model);

        assertEquals("update-todo-item", result);
    }

    @Test
    void testUpdateTodoItemWithoutValidationError() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String result = todoItemController.updateTodoItem(1L, new TodoItem(), bindingResult, model);

        assertEquals("redirect:/", result);
        verify(todoItemService, times(1)).update(eq(1L), any(TodoItem.class));
    }
}



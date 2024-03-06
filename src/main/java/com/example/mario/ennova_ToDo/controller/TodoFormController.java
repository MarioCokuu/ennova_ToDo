package com.example.mario.ennova_ToDo.controller;


import com.example.mario.ennova_ToDo.entity.TodoItem;
import com.example.mario.ennova_ToDo.service.TodoFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TodoFormController {

    @Autowired
    private TodoFormService todoFormService;

    @GetMapping("/create-todo")
    public String showCreateForm(TodoItem todoItem) {
        return "add-todo-item";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {

        TodoItem todoItem = todoFormService.findById(id);
        model.addAttribute("todo", todoItem);
        return "update-todo-item";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") long id, Model model) {

        todoFormService.delete(id);
        return "redirect:/";
    }
}



package com.jinho.springboot.todowebapp.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoController {
    private final TodoService todoService;
    public TodoController(TodoService todoService) {
        this.todoService = new TodoService();
    }
    @RequestMapping("/list-todos")
    public String listAllTodos( ModelMap model) {
        model.put("todos", todoService.findByUsername(""));
        return "todo-list";
    }
}

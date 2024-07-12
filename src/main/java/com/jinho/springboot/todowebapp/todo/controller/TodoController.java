package com.jinho.springboot.todowebapp.todo.controller;

import com.jinho.springboot.todowebapp.todo.entity.Todo;
import com.jinho.springboot.todowebapp.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;

@Controller
@SessionAttributes("name")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("/list-todos")
    public String listAllTodos( ModelMap model) {
        String username = getName();
        model.put("todos", todoService.findByUsername(username));
        return "listTodos";
    }

    @GetMapping("/add-todo")
    public String showNewTodoPage(ModelMap model) {
        String username = getName();
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1),false);
        model.put("todo", todo);
        return "todo";
    }

    @PostMapping("/add-todo")
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "todo";
        }
//        String username = getName();
//        todoService.addTodo(username,todo.getDescription(),todo.getTargetDate(),todo.isDone());
        todo.setUsername(getName());
        System.out.println(todo.getId());
        todoService.addTodo(todo);
        return "redirect:/list-todos";
    }

    @GetMapping("/delete-todo")
    public String deleteTodo(ModelMap model, int id) {
        todoService.deleteById(id);
        return "redirect:/list-todos";
    }

    @GetMapping("/update-todo")
    public String updateTodo(ModelMap model, int id) {
        Todo todo = todoService.findById(id).orElse(null);
        model.put("todo", todo);
        return "todo";
    }

    @PostMapping("/update-todo")
    public String doUpdateTodo(ModelMap model, Todo todo, BindingResult result) {
        if(result.hasErrors()){
            return "todo";
        }
        String username = getName();
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }




    private static String getName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}

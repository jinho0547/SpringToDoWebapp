package com.jinho.springboot.todowebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
        return "listTodos";
    }

    @GetMapping("/add-todo")
    public String showNewTodoPage(ModelMap model) {
        String username = (String)model.get("name");
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1),false);
        model.put("todo", todo);
        return "todo";
    }
    @PostMapping("/add-todo")
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "todo";
        }
        String username = model.get("name").toString();
        todoService.addTodo(username,todo.getDescription(),todo.getTargetDate(),false);
        return "redirect:/list-todos";
    }

    @GetMapping("/delete-todo")
    public String deleteTodo(ModelMap model, int id) {
        todoService.deleteById(id);
        return "redirect:/list-todos";
    }

    @GetMapping("/update-todo")
    public String updateTodo(ModelMap model, int id) {
        Todo todo = todoService.findById(id);
        model.put("todo", todo);
        return "todo";
    }

    @PostMapping("/update-todo")
    public String doUpdateTodo(ModelMap model, Todo todo, BindingResult result) {
        if(result.hasErrors()){
            return "todo";
        }
        String username = model.get("name").toString();
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }
}

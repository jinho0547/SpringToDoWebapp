package com.jinho.springboot.todowebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;
    static {
        todos.add(new Todo(++todosCount, "jinho", "Learn Spring", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "jinho", "Learn DevOps", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todosCount, "jinho", "Learn AWS", LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUsername(String username) {
        return todos;
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        return todos.stream().filter(predicate).findFirst().orElse(null);
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
        todos.add(new Todo(++todosCount, username, description, targetDate, done));
    }

    public void deleteById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public void updateTodo(Todo todo) {
//        Todo asd = todos.stream().filter(_todo -> _todo.getId() == todo.getId()).findFirst().orElse(null);
        deleteById(todo.getId());
        todos.add(todo);
    }
}

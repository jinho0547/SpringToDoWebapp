package com.jinho.springboot.todowebapp.todo.service;

import com.jinho.springboot.todowebapp.todo.entity.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class TodoServiceInMemoryImpl implements TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;
    static {
        todos.add(new Todo(++todosCount, "jinho", "Learn Spring", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "jinho", "Learn DevOps", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todosCount, "jinho", "Learn AWS", LocalDate.now().plusYears(3), false));
        todos.add(new Todo(++todosCount, "admin", "Learn Java 1", LocalDate.now().plusYears(3), false));
        todos.add(new Todo(++todosCount, "admin", "Learn Java 2", LocalDate.now().plusYears(3), false));
        todos.add(new Todo(++todosCount, "admin", "Learn Java 3", LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUsername(String username) {
        return todos.stream().filter(todo -> todo.getUsername().equals(username)).toList();
    }

    public Optional<Todo> findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().orElse(null);
        return Optional.ofNullable(todo);
    }

    public void addTodo(Todo todo) {
        todo.setId(++todosCount);
        todos.add(todo);
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

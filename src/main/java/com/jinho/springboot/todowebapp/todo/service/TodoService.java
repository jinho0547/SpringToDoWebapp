package com.jinho.springboot.todowebapp.todo.service;

import com.jinho.springboot.todowebapp.todo.entity.Todo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> findByUsername(String username);

    Optional<Todo> findById(int id);

    void addTodo(Todo todo);

    void deleteById(int id);

    void updateTodo(Todo todo);
}

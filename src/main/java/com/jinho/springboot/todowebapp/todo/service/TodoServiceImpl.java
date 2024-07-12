package com.jinho.springboot.todowebapp.todo.service;

import com.jinho.springboot.todowebapp.todo.entity.Todo;
import com.jinho.springboot.todowebapp.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    private TodoRepository repository;
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.repository = todoRepository;
    }

    @Override
    public List<Todo> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Optional<Todo> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void addTodo(Todo todo) {
        repository.save(todo);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public void updateTodo(Todo todo) {
        repository.save(todo);
    }
}

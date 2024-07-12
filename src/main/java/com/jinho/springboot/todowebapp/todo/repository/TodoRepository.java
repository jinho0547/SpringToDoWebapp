package com.jinho.springboot.todowebapp.todo.repository;

import com.jinho.springboot.todowebapp.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    List<Todo> findByUsername(String username);
}

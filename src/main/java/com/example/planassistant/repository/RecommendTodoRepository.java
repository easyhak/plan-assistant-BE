package com.example.planassistant.repository;

import com.example.planassistant.domain.RecommendTodo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecommendTodoRepository extends JpaRepository<RecommendTodo, Long> {
}

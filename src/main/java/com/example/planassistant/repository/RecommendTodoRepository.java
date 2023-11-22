package com.example.planassistant.repository;

import com.example.planassistant.domain.Member;
import com.example.planassistant.domain.RecommendTodo;
import com.example.planassistant.domain.Todo;
import com.example.planassistant.dto.RecommendTodoResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface RecommendTodoRepository extends JpaRepository<RecommendTodo, Long> {
//    @Query(value = "select r from RecommendTodo r where r.todo = :member and r.startTime between :start and :end")
    List<RecommendTodo> findRecommendTodoByStartTimeBetween(LocalDateTime start, LocalDateTime end);
    List<RecommendTodo> findRecommendTodoByTodo(Todo todo);
}

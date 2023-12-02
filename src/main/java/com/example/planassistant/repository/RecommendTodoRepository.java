package com.example.planassistant.repository;

import com.example.planassistant.domain.RecommendTodo;
import com.example.planassistant.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface RecommendTodoRepository extends JpaRepository<RecommendTodo, Long> {
//    @Query(value = "select r from RecommendTodo r where r.todo = :member and r.startTime between :start and :end")
    List<RecommendTodo> findRecommendTodoByStartTimeBetween(LocalDateTime start, LocalDateTime end);
    List<RecommendTodo> findRecommendTodoByTodo(Todo todo);

    Optional<RecommendTodo> findByTodo_Id(Long id);
}

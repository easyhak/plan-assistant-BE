package com.example.planassistant.dto;

import com.example.planassistant.domain.RecommendTodo;
import com.example.planassistant.domain.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RecommendTodoResDto {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Todo todo;

    public RecommendTodoResDto(RecommendTodo todo) {
        this.id = todo.getId();
        this.startTime = todo.getStartTime();
        this.endTime = todo.getEndTime();
        this.todo = todo.getTodo();
    }
}

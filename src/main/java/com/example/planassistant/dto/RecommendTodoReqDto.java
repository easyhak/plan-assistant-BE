package com.example.planassistant.dto;

import com.example.planassistant.domain.RecommendTodo;
import com.example.planassistant.domain.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RecommendTodoReqDto {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    // 추천 todo id
    private Long id;
    public RecommendTodo toEntity(RecommendTodoReqDto dto, Todo todo) {
        return RecommendTodo.builder()
                .todo(todo)
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .build();
    }
}

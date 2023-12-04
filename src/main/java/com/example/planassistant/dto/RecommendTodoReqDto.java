package com.example.planassistant.dto;

import com.example.planassistant.domain.RecommendTodo;
import com.example.planassistant.domain.Todo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public class RecommendTodoReqDto {

    @Schema(description = "시작 시간", nullable = true, example = "2023-10-12 10:00")
    private String startTime;
    @Schema(description = "끝 시간", nullable = true, example = "2023-10-12 12:00")
    private String endTime; // string으로 받아서 해줘야함
    // 추천 todo id
    private Long id;
    public RecommendTodo toEntity(RecommendTodoReqDto dto, Todo todo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return RecommendTodo.builder()
                .todo(todo)
                .startTime(LocalDateTime.parse(dto.getStartTime(), formatter))
                .endTime(LocalDateTime.parse(dto.getEndTime(), formatter))
                .build();
    }
}

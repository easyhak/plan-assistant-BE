package com.example.planassistant.dto;


import com.example.planassistant.domain.Todo;
import com.example.planassistant.domain.TodoPlan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoPlanResDto {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "시작 시간")
    private LocalDateTime startTime;
    @Schema(description = "끝나는 시간")
    private LocalDateTime endTime;

    @Schema(description = "장소")
    private String place;
    @Schema(description = "내용")
    private String content;
    private Double longitude;
    private Double latitude;

    @Schema(description = "todo의 id")
    private Long todo_id;

    public TodoPlanResDto(TodoPlan todoPlan, Todo todo){
        this.id = todoPlan.getId();
        this.startTime = todoPlan.getStartTime();
        this.endTime = todoPlan.getEndTime();
        this.place = todo.getPlace();
        this.content = todo.getContent();
        this.longitude = todo.getLongitude();
        this.latitude = todo.getLatitude();
        this.todo_id = todo.getId();
    }
}

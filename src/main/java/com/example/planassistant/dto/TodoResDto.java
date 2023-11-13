package com.example.planassistant.dto;

import com.example.planassistant.domain.Todo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "Todo Response Dto")
public class TodoResDto {
    @Schema(description = "id", nullable = false, example = "1")
    private Long id;
    @Schema(description = "장소", nullable = true, example = "중앙대학교")
    private String place;
    @Schema(description = "내용", nullable = true, example = "리눅스 과제하기")
    private String content;
    @Schema(description = "우선순위", nullable = true, example = "3")
    private Integer priority;
    @Schema(description = "위도", nullable = true, example = "111.1111")
    private Double latitude;
    @Schema(description = "경도", nullable = true, example = "122.1111")
    private Double longitude;
    @Schema(description = "마감기한", nullable = true, example = "2023-12-12 10:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadline;
    @Schema(description = "업데이트 시간", nullable = true, example = "2023-10-12 09:30")
    private LocalDateTime updateDate;
    private Boolean complete;
    public TodoResDto(Todo todo){
        this.id = todo.getId();
        this.place = todo.getPlace();
        this.priority = todo.getPriority();
        this.longitude = todo.getLongitude();
        this.latitude = todo.getLatitude();
        this.deadline = todo.getDeadline();
        this.updateDate = todo.getUpdateDate();
        this.content = todo.getContent();
        this.complete = todo.getComplete();
    }
}


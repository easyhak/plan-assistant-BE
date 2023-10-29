package com.example.planassistant.dto;

import com.example.planassistant.domain.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoResDto {
    private Long id;
    private String place;
    private String content;
    private Integer priority;
    private LocalDateTime deadline;
    private LocalDateTime updateDate;
    private Boolean complete;
    public TodoResDto(Todo todo){
        this.id = todo.getId();
        this.place = todo.getPlace();
        this.priority = todo.getPriority();
        this.deadline = todo.getDeadline();
        this.updateDate = todo.getUpdateDate();
        this.content = todo.getContent();
        this.complete = todo.getComplete();
    }
}


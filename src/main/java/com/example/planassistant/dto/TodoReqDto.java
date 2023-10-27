package com.example.planassistant.dto;

import com.example.planassistant.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class TodoReqDto {
    private String place;
    private Integer priority;
    private LocalDate deadline;

}

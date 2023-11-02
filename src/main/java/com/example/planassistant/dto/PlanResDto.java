package com.example.planassistant.dto;

import com.example.planassistant.domain.Plan;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class PlanResDto {

    private Long id;
    private String place;
    private String latitude;
    private String longitude;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    public PlanResDto(Plan plan) {
        this.id = plan.getId();
        this.place = plan.getPlace();
        this.latitude = plan.getLatitude();
        this.longitude = plan.getLongitude();
        this.content = plan.getContent();
        this.startTime = plan.getStartTime();
        this.endTime = plan.getEndTime();
    }
}

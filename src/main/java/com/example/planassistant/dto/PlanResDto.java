package com.example.planassistant.dto;

import com.example.planassistant.domain.Plan;
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
    private LocalDateTime startTime;
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

package com.example.planassistant.dto;

import com.example.planassistant.domain.Plan;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Schema(description = "Plan Response Dto")
public class PlanResDto {

    @Schema(description = "Id", nullable = false, example = "1")
    private Long id;
    @Schema(description = "장소", example = "중앙대학교")
    private String place;
    @Schema(description = "위도")
    private Double latitude;
    @Schema(description = "위도")
    private Double longitude;
    @Schema(description = "내용", example = "캡스톤 회의")
    private String content;

    @Schema(description = "시작 시간", nullable = true, example = "2023-10-12 10:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;
    @Schema(description = "시작 시간", nullable = true, example = "2023-10-12 12:00")
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

package com.example.planassistant.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Schema(description = "Plan Response Dto")
public class PlanReqDto {

    @Schema(description = "장소", example = "중앙대학교")
    private String place;
    @Schema(description = "내용", example = "캡스톤 회의")
    private String content;

    @Schema(description = "시작 시간", nullable = true, example = "2023-10-12 10:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;

    @Schema(description = "시작 시간", nullable = true, example = "2023-10-12 12:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    @Schema(description = "위도", nullable = true, example = "2023-10-12 12:00")
    private Long latitude;
    @Schema(description = "경도", nullable = true, example = "2023-10-12 12:00")
    private Long longitude;
}

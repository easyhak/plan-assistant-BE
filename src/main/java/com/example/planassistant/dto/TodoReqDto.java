package com.example.planassistant.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@ToString
@Schema(description = "Todo Request Dto")
public class TodoReqDto {
    @Schema(description = "장소", nullable = true, example = "중앙대학교")
    private String place;
    @Schema(description = "우선순위", nullable = false, example = "1")
    private Integer priority;
    @Schema(description = "내용", nullable = false, example = "리눅스 과제하기")
    private String content;
    @Schema(description = "마감기한", nullable = true, example = "2023-12-12 10:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadline;
    @Schema(description = "위도")
    private Long latitude;
    @Schema(description = "경도")
    private Long longitude;
}

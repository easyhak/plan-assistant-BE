package com.example.planassistant.dto.lifepattern;

import com.example.planassistant.domain.enumType.DayOfTheWeek;
import com.example.planassistant.domain.enumType.Life;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Builder
@Schema(description = "생활패턴 Dto")
public class LifePatternReqDto {
    @Schema(description = "생활패턴 내용", nullable = false, implementation = Life.class, example = "SLEEPING_TIME")
    private Life life;
    @Schema(description = "요일", nullable = false, implementation = DayOfTheWeek.class, example = "MON")
    private DayOfTheWeek dayOfTheWeek;
    @Schema(description = "시작 시간", nullable = false ,example = "02:00")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @Schema(description = "끝나는 시간", nullable = false ,example = "10:00")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;
}

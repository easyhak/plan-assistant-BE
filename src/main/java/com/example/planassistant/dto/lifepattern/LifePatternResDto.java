package com.example.planassistant.dto.lifepattern;

import com.example.planassistant.domain.LifePattern;
import com.example.planassistant.domain.enumType.Life;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class LifePatternResDto {
    @Schema(description = "Id", nullable = false)
    private Integer id;
    @Schema(description = "생활패턴 내용", nullable = false, implementation = Life.class, example = "SLEEPING_TIME")
    private Life life;
    @Schema(description = "시작 시간", nullable = false ,example = "02:00")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @Schema(description = "끝나는 시간", nullable = false ,example = "10:00")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    public LifePatternResDto(LifePattern lifePattern){
        this.id = lifePattern.getId();
        this.life = lifePattern.getLife();
        this.startTime = lifePattern.getStartTime();
        this.endTime = lifePattern.getEndTime();
    }

}

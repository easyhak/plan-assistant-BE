package com.example.planassistant.dto.lifepattern;

import com.example.planassistant.domain.LifePattern;
import com.example.planassistant.domain.enumType.Life;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class LifePatternResDto {
    private Integer id;
    private Life life;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    public LifePatternResDto(LifePattern lifePattern){
        this.id = lifePattern.getId();
        this.life = lifePattern.getLife();
        this.startTime = lifePattern.getStartDateTime();
        this.endTime = lifePattern.getEndDateTime();
    }

}

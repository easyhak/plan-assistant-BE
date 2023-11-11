package com.example.planassistant.domain;

import com.example.planassistant.domain.enumType.DayOfTheWeek;
import com.example.planassistant.domain.enumType.Life;
import com.example.planassistant.dto.lifepattern.LifePatternReqDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

// 생활패턴 입력
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LifePattern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // enum 값으로 입력 받기
    @Enumerated(EnumType.STRING)
    private Life life; // SLEEPING_TIME, NOT_FOCUS_TIME, WORKING_TIME, FOCUS_TIME
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public LifePattern(LifePatternReqDto dto, Member member) {
        this.life = dto.getLife();
        this.endTime = dto.getEndTime();
        this.member = member;
    }

    @Builder
    public LifePattern(Life life,DayOfTheWeek dayOfTheWeek,LocalTime startDateTime, LocalTime endDateTime){
        this.life = life;
        this.startTime = startDateTime;
        this.endTime = endDateTime;
    }

    public void setLife(Life life) {
        this.life = life;
    }

    public void setStartTime(LocalTime startDateTime) {
        this.startTime = startDateTime;
    }

    public void setEndTime(LocalTime endDateTime) {
        this.endTime = endDateTime;
    }

}


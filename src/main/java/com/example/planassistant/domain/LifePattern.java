package com.example.planassistant.domain;

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
    private Life life; // SLEEPING_TIME, NOT_FOCUS_TIME, WORKING_TIME
    private LocalTime startDateTime;
    private LocalTime endDateTime;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public LifePattern(LifePatternReqDto dto, Member member) {
        this.life = dto.getLife();
        this.startDateTime = dto.getStartTime();
        this.endDateTime = dto.getEndTime();
        this.member = member;
    }

    @Builder
    public LifePattern(Life life, LocalTime startDateTime, LocalTime endDateTime){
        this.life = life;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public void setLife(Life life) {
        this.life = life;
    }

    public void setStartDateTime(LocalTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(LocalTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}


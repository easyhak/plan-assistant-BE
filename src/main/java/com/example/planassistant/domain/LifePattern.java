package com.example.planassistant.domain;

import com.example.planassistant.domain.enumType.Life;
import com.example.planassistant.dto.lifepattern.LifePatternReqDto;
import com.example.planassistant.dto.lifepattern.LifePatternResDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

// 생활패턴 입력
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LifePattern {
    @Id
    private Integer id;

    // enum 값으로 입력 받기
    @Enumerated(EnumType.STRING)
    private Life life; // 수면, 집중 잘되는, 집중 안되는
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


}


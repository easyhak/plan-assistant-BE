package com.example.planassistant.domain;

import com.example.planassistant.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
// 1:N 매핑 Member : Plan
public class Plan {

    @Id
    private Long id;
    private String content;
    private String place;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @Builder
    public Plan(String content, String place, LocalDateTime startTime, LocalDateTime endTime, Member member) {
        this.content = content;
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
        this.member = member;
    }
}

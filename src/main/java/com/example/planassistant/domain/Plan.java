package com.example.planassistant.domain;

import com.example.planassistant.common.BaseTimeEntity;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "varChar(255) default ''")
    private String place;
    @Column(nullable = false, columnDefinition = "varChar(255) default ''")
    private String content;
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

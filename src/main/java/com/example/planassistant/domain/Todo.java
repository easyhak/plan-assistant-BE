package com.example.planassistant.domain;


import com.example.planassistant.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 1:N 매핑
public class Todo extends BaseTimeEntity {
    @Id
    private Long id;

    private String place;

    private Integer priority;

    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Todo(String place, Integer priority, LocalDate deadline, Member member){
        this.place = place;
        this.priority = priority;
        this.deadline = deadline;
        this.member = member;

    }
}

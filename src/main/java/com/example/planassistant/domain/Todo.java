package com.example.planassistant.domain;


import com.example.planassistant.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 1:N 매핑
public class Todo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String place;
    private String content;
    private Integer priority;

    private LocalDateTime deadline;

    private String latitude;
    private String longitude;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Todo(String place,String content, Integer priority, LocalDateTime deadline,String latitude, String longitude, Member member){
        this.place = place;
        this.priority = priority;
        this.deadline = deadline;
        this.content = content;
        this.member = member;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

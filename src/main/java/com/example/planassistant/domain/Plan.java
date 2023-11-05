package com.example.planassistant.domain;

import com.example.planassistant.common.BaseTimeEntity;
import com.example.planassistant.dto.PlanReqDto;
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
    private Long latitude;
    private Long longitude;
    @Column(nullable = false, columnDefinition = "varChar(255) default ''")
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @Builder
    public Plan(String content, String place, LocalDateTime startTime, LocalDateTime endTime, Long latitude, Long longitude, Member member) {
        this.content = content;
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
        this.member = member;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public Plan(PlanReqDto planReqDto, Member member){
        this.content = planReqDto.getContent();
        this.member = member;
        this.endTime = planReqDto.getEndTime();
        this.startTime = planReqDto.getStartTime();
        this.latitude = planReqDto.getLatitude();
        this.longitude = planReqDto.getLongitude();
        this.place = planReqDto.getPlace();

    }
    public void changePlan(PlanReqDto planReqDto){
        this.content = planReqDto.getContent();
        this.place = planReqDto.getPlace();
        this.startTime = planReqDto.getStartTime();
        this.endTime = planReqDto.getEndTime();
        this.latitude = planReqDto.getLatitude();
        this.longitude = planReqDto.getLongitude();

    }
}

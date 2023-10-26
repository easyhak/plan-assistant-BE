package com.example.planassistant.domain;

import com.example.planassistant.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
// 1:N 매핑
public class Plan extends BaseTimeEntity {

    @Id
    private Long id;

    private String place;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Builder
    public Plan(String place, LocalDateTime startTime, LocalDateTime endTime) {
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

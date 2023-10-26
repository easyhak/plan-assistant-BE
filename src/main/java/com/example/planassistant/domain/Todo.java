package com.example.planassistant.domain;


import com.example.planassistant.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
// 1:N 매핑
public class Todo extends BaseTimeEntity {
    @Id
    private Long id;

    private String place;

    private Integer priority;

}

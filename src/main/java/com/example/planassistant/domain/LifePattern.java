package com.example.planassistant.domain;

import com.example.planassistant.domain.enumType.Life;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

// 생활패턴 입력
@Getter
@Entity
public class LifePattern {
    @Id
    private Integer id;

    // enum 값으로 입력 받기
    @Enumerated(EnumType.STRING)
    private Life content; // 수면, 집중 잘되는, 집중 안되는
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

}

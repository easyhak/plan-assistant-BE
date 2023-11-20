package com.example.planassistant.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendTodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Todo todo;

    @Builder
    public RecommendTodo(LocalDateTime startTime, LocalDateTime endTime, Todo todo) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.todo = todo;
    }
}

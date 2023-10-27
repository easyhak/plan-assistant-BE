package com.example.planassistant.domain;


import com.example.planassistant.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

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

    @Column(nullable = false, columnDefinition = "varChar(255) default ''")
    private String place;

    @Column(nullable = false, columnDefinition = "varChar(255) default ''")
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

    public void setContent(String content) {
        this.content = content;
    }
}

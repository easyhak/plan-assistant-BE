package com.example.planassistant.domain;


import com.example.planassistant.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
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

    private Double latitude;
    private Double longitude;
    private Boolean complete;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @Builder
    public Todo(String place,String content, Integer priority, LocalDateTime deadline, Double latitude, Double longitude, Member member){
        this.place = place;
        this.priority = priority;
        this.deadline = deadline;
        this.content = content;
        this.member = member;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @PrePersist
    public void insertTodo(){
        this.complete = this.complete == null ? false : this.complete;
    }
    public void setComplete(Boolean complete){
        this.complete = complete;
    }
}

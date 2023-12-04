package com.example.planassistant.domain;

import com.example.planassistant.common.BaseTimeEntity;
import com.example.planassistant.domain.enumType.Thing;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Importance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Thing name;

    private Integer degree;

    private Double weight;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @Builder
    public Importance(Thing name, Integer degree, Member member){
        this.name = name;
        this.degree = degree;
        this.member = member;
    }
}

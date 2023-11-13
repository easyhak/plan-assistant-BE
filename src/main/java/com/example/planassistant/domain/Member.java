package com.example.planassistant.domain;

import com.example.planassistant.common.BaseTimeEntity;
import com.example.planassistant.domain.enumType.Life;
import com.example.planassistant.domain.enumType.Thing;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @UuidGenerator
    private String id;

    private String email;

    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LifePattern> lifePatterns = new ArrayList<>();


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Importance> importanceList = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String email, String password, Authority authority) {
        this.email = email;
        this.password = password;
        this.authority = authority;
    }
    @PostLoad
    @PostConstruct
    public void addDefault(){
        if(lifePatterns.isEmpty()){

            var sleepTime =  LifePattern.builder()
                    .life(Life.SLEEPING_TIME)
                    .endDateTime(LocalTime.of(8,0,0))
                    .startDateTime(LocalTime.of(0, 0 , 0))
                    .build();

            var notFocusTime = LifePattern.builder()
                    .life(Life.NOT_FOCUS_TIME)
                    .endDateTime(LocalTime.of(13,0,0))
                    .startDateTime(LocalTime.of(12, 0 , 0))
                    .build();
            var focusTime = LifePattern.builder()
                    .life(Life.FOCUS_TIME)
                    .endDateTime(LocalTime.of(21,0,0))
                    .startDateTime(LocalTime.of(19, 0 , 0))
                    .build();
            sleepTime.setMember(this);
            focusTime.setMember(this);
            notFocusTime.setMember(this);
            this.lifePatterns.add(sleepTime);
            this.lifePatterns.add(focusTime);
            this.lifePatterns.add(notFocusTime);

        }
        if(importanceList.isEmpty()){
            var notFocusTime = Importance.builder().
                    degree(5).
                    name(Thing.NOT_FOCUS_TIME).member(this)
                    .build();

            var focusTime = Importance.builder().
                    degree(4).
                    name(Thing.FOCUS_TIME).member(this)
                    .build();
            var distance = Importance.builder().
                    degree(3).name(Thing.DISTANCE).member(this)
                    .build();
            var priority = Importance.builder().degree(2).member(this)
                    .name(Thing.PRIORITY).build();
            var deadline = Importance.builder().degree(1).member(this)
                    .name(Thing.DEADLINE).build();

            this.importanceList.add(focusTime);
            this.importanceList.add(notFocusTime);
            this.importanceList.add(distance);
            this.importanceList.add(priority);
            this.importanceList.add(deadline);

        }
    }


}

package com.example.planassistant.domain;

import com.example.planassistant.common.BaseTimeEntity;
import com.example.planassistant.domain.enumType.Life;
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
    public void addDefaultLifePattern(){
        if(lifePatterns.isEmpty()){

            var sleepTime =  LifePattern.builder()
                    .life(Life.SLEEPING_TIME)
                    .endDateTime(LocalTime.of(8,0,0))
                    .startDateTime(LocalTime.of(0, 0 , 0))
                    .build();

            var notFocusTime = LifePattern.builder()
                    .life(Life.NOT_FOCUS_TIME)
                    .endDateTime(LocalTime.of(12,0,0))
                    .startDateTime(LocalTime.of(13, 0 , 0))
                    .build();
            var focusTime = LifePattern.builder()
                    .life(Life.FOCUS_TIME)
                    .endDateTime(LocalTime.of(19,0,0))
                    .startDateTime(LocalTime.of(21, 0 , 0))
                    .build();
            sleepTime.setMember(this);
            focusTime.setMember(this);
            notFocusTime.setMember(this);
            this.lifePatterns.add(sleepTime);
            this.lifePatterns.add(focusTime);
            this.lifePatterns.add(notFocusTime);

        }
    }

}

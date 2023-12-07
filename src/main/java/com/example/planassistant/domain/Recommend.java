package com.example.planassistant.domain;

import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@Document(collection = "Recommend")
@ToString
public class Recommend {
    @Id
    private String memberId;
    private Object recommend;

    @Builder
    public Recommend(String memberId, Object recommend){
        this.memberId = memberId;
        this.recommend = recommend;
    }
}

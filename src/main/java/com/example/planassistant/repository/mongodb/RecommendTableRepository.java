package com.example.planassistant.repository.mongodb;

import com.example.planassistant.domain.Recommend;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RecommendTableRepository extends MongoRepository<Recommend, String> {
//    Optional<Recommend> findRecommendByMemberId(String memberId);

    List<Recommend> findRecommendByMemberId(String memberId);
    void deleteAllByMemberId(String member);
    void deleteRecommendByMemberId(String memberId);
}

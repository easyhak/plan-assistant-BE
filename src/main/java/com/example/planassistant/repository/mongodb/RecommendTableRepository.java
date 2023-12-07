package com.example.planassistant.repository.mongodb;

import com.example.planassistant.domain.Recommend;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RecommendTableRepository extends MongoRepository<Recommend, String> {
    Optional<Recommend> findRecommendByMemberId(String memberId);
    void deleteRecommendByMemberId(String memberId);
}

package com.example.planassistant.repository;

import com.example.planassistant.domain.LifePattern;
import com.example.planassistant.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LifePatternRepository extends JpaRepository<LifePattern, Long> {
    List<LifePattern> findByMember(Member member);
}

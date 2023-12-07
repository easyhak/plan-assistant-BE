package com.example.planassistant.repository.mysql;

import com.example.planassistant.domain.LifePattern;
import com.example.planassistant.domain.Member;
import com.example.planassistant.domain.enumType.Life;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LifePatternRepository extends JpaRepository<LifePattern, Long> {
    List<LifePattern> findByMember(Member member);
    List<LifePattern> findByMemberAndLife(Member member, Life life);
}

package com.example.planassistant.repository;

import com.example.planassistant.domain.Member;
import com.example.planassistant.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findPlanByMemberOrderByStartTime(Member member);
}

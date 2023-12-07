package com.example.planassistant.repository.mysql;

import com.example.planassistant.domain.Member;
import com.example.planassistant.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findPlanByMemberOrderByStartTimeDesc(Member member);

    List<Plan> findPlanByMemberAndStartTimeBetweenOrderByStartTime(Member member, LocalDateTime start, LocalDateTime end);


    List<Plan> findPlanByMemberAndStartTimeBetween(Member member, LocalDateTime start, LocalDateTime end);

//    TIME_TO_SEC(TIMEDIFF(r.startTime, r.endTime)) / 60
    // category에 따른 startTime과 endTime의 차에 대한 평균 값을 계산하는 쿼리
    @Query("SELECT AVG(TIME_TO_SEC(TIMEDIFF(p.endTime, p.startTime)) / 60) " +
            "FROM Plan p " +
            "WHERE p.category = :category")
    Double findAverageDurationByCategory(@Param("category") String category);

    // category에 따라 startTime과 endTime의 차를 조회하는 쿼리
    List<Plan> findByCategory(String category);

    void deleteByMember(Member member);
}

package com.example.planassistant.repository;


import com.example.planassistant.domain.TodoPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoPlanRepository extends JpaRepository<TodoPlan, Long> {
}

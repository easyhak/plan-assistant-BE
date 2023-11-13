package com.example.planassistant.repository;

import com.example.planassistant.domain.Importance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportanceRepository extends JpaRepository<Importance, Long> {
}

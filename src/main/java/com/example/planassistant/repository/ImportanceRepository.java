package com.example.planassistant.repository;

import com.example.planassistant.domain.Importance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImportanceRepository extends JpaRepository<Importance, Long> {
    List<Importance> findByMember_Id(String id);
}

package com.example.planassistant.repository.mysql;

import com.example.planassistant.domain.Importance;
import com.example.planassistant.domain.enumType.Thing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImportanceRepository extends JpaRepository<Importance, Long> {
    List<Importance> findByMember_Id(String id);
    Optional<Importance> findByMember_IdAndName(String id, Thing name);
}

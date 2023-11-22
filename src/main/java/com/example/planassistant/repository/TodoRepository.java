package com.example.planassistant.repository;


import com.example.planassistant.domain.Member;
import com.example.planassistant.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findTodoByMemberOrderByUpdateDate(Member member);
    List<Todo> findByMemberAndCompleteOrderByUpdateDate(Member member, Boolean complete);

    Long countByMember(Member member);

    Long countByMemberAndComplete(Member member, Boolean complete);

    Optional<Todo> findTodoByMemberAndId(Member member, Long id);
}

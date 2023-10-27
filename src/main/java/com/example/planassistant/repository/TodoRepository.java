package com.example.planassistant.repository;


import com.example.planassistant.domain.Member;
import com.example.planassistant.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    public List<Todo> findTodoByMemberOrderByUpdateDate(Member member);
}

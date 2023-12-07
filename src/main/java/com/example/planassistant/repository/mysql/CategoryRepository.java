package com.example.planassistant.repository.mysql;


import com.example.planassistant.domain.Category;
import com.example.planassistant.domain.Member;
import com.example.planassistant.dto.CategoryResDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByMemberAndName(Member member, String name);

    List<Category> findByMember(Member member);

    void deleteByMember(Member member);
}

package com.example.planassistant.service;

import com.example.planassistant.domain.Category;
import com.example.planassistant.dto.CategoryReqDto;
import com.example.planassistant.repository.mysql.CategoryRepository;
import com.example.planassistant.repository.mysql.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public Category createCategory(String userId, CategoryReqDto dto){
//        dto.getName();
        var member = memberRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
        );
        categoryRepository.findByMemberAndName(member, dto.getName()).ifPresent(
                category -> {throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");}
        );
        var category = Category.builder()
                .name(dto.getName())
                .member(member)
                .build();
        return categoryRepository.save(category);
//        return categoryRepository.save(category);
    }


    public List<String> getCategories(String username) {
        var member = memberRepository.findById(username).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
        );
        List<Category> categories = categoryRepository.findByMember(member);
        List<String> categoryList = new ArrayList<>();
        for (Category category : categories) {
            categoryList.add(category.getName());
        }
        return categoryList;
    }
}

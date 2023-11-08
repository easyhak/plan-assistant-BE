package com.example.planassistant.service;

import com.example.planassistant.repository.TodoPlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoPlanService {
    private final TodoPlanRepository todoPlanRepository;

    // mock 값들을 저장
    public void saveMockTodoPlans(){

    }
    // mock 값인 todoPlans들을 가져오기
    public void getMockTodoPlans(){

    }
}

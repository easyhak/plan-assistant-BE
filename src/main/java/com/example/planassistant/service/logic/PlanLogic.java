package com.example.planassistant.service.logic;


import com.example.planassistant.domain.Plan;
import com.example.planassistant.domain.Todo;
import com.example.planassistant.dto.TodoReqDto;
import com.example.planassistant.repository.MemberRepository;
import com.example.planassistant.repository.PlanRepository;
import com.example.planassistant.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PlanLogic {

    private final MemberRepository memberRepository;
    private final PlanRepository planRepository;
    private final TodoRepository todoRepository;
    // 날짜 간격으로 Plan 가져오기

    private List<Plan> getPlansByDate(String memberId, Integer addDate){
        var member = memberRepository.findById(memberId)
                .orElseThrow(()-> new NoSuchElementException("member not fount"));
        var nowDate = LocalDate.now();
        var plusDate = nowDate.plusDays(addDate);
        var plans = planRepository.findPlanByMemberAndStartTimeBetween(member, nowDate, plusDate);
        return plans;
    }

    // 모든 Todo 가져오기
    private List<Todo> getAllTodos(){
        var todos = todoRepository.findAll();
        return todos;
    }
    // 거리 계산 함수
    private ArrayList<Long> calculateDistance(){

        return null;
    }

    // 가중치 계산 함수
    
}

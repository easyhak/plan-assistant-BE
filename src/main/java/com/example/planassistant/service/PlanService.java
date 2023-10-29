package com.example.planassistant.service;

import com.example.planassistant.domain.Plan;
import com.example.planassistant.dto.PlanReqDto;
import com.example.planassistant.dto.PlanResDto;
import com.example.planassistant.repository.MemberRepository;
import com.example.planassistant.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public void makePlan(String memberId, PlanReqDto planReqDto){
        var member = memberRepository.findById(memberId).orElseThrow(
                ()->new NoSuchElementException("member not found")
        );

        planRepository.save(new Plan(planReqDto, member));
    }

    @Transactional(readOnly = true)
    public PlanResDto getPlanById(Long planId){
        var plan = planRepository.findById(planId).orElseThrow(
                ()->new NoSuchElementException("member not found")
        );
        return new PlanResDto(plan);
    }

    @Transactional(readOnly = true)
    public  List<PlanResDto> getPlanByMember(String memberId){
        var member = memberRepository.findById(memberId).orElseThrow(
                ()->new NoSuchElementException("member not found")
        );
        var plans = planRepository.findPlanByMemberOrderByStartTime(member);
        List<PlanResDto> planResDtoList = new ArrayList<>();
        for(Plan x: plans){
            planResDtoList.add(new PlanResDto(x));
        }
        return planResDtoList;
    }
    @Transactional
    public String changePlan(Long planId, PlanReqDto planReqDto){
        log.info(planReqDto.toString());
        var plan = planRepository.findById(planId).orElseThrow(
                ()->new NoSuchElementException("plan not found")
        );
        plan.changePlan(planReqDto);
        return "plan changed";
    }

    @Transactional
    public String deletePlan(Long planId){
        var plan = planRepository.findById(planId).orElseThrow(
                ()->new NoSuchElementException("plan not found")
        );
        planRepository.delete(plan);
        return "plan deleted";
    }
}

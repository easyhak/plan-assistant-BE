package com.example.planassistant.service;

import com.example.planassistant.domain.Plan;
import com.example.planassistant.dto.PlanReqDto;
import com.example.planassistant.dto.PlanResDto;
import com.example.planassistant.repository.MemberRepository;
import com.example.planassistant.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        var plan = new Plan(planReqDto, member);
        log.info(plan.getContent(), plan.getPlace());
        planRepository.save(plan);
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


    // 현재 날짜 + 1 에 addDate한 날짜까지의 plan을 가져온다.
    // 예시: 오늘 날짜가 11월 7일이고 addDate가 3이면 11월 8일부터 11월 11일까지의 plan 리스트를 가져온다.
    @Transactional(readOnly = true)
    public List<PlanResDto> getPlansByAddDate(String memberId, Integer addDate){
        var member = memberRepository.findById(memberId)
                .orElseThrow(()-> new NoSuchElementException("member not fount"));
        var nowDate = LocalDate.now().plusDays(1);
        var startDateTime = nowDate.atStartOfDay();
        var plusDateTime = nowDate.plusDays(addDate).atTime(LocalTime.MAX);
        System.out.println(startDateTime);
        System.out.println(plusDateTime);
        var plans = planRepository.findPlanByMemberAndStartTimeBetweenOrderByStartTime(member, startDateTime, plusDateTime);
        List<PlanResDto> planResDtoList = new ArrayList<>();
        for(var x: plans){
            planResDtoList.add(new PlanResDto(x));
        }
        return planResDtoList;
    }

    // 날짜로 plan 가져오기
    @Transactional(readOnly = true)
    public List<PlanResDto> getPlansByStartDate(String memberId, LocalDate startDate){
        var member = memberRepository.findById(memberId)
                .orElseThrow(()-> new NoSuchElementException("member not fount"));
        LocalDateTime start = startDate.atStartOfDay(); // 2021-10-25 00:00:00.00000000
        LocalDateTime end = startDate.atTime(LocalTime.MAX);
        var plans = planRepository.findPlanByMemberAndStartTimeBetween(member, start, end);
        List<PlanResDto> planResDtoList = new ArrayList<>();
        for(var x: plans){
            planResDtoList.add(new PlanResDto(x));
        }
        return planResDtoList;

    }

    @Transactional(readOnly = true)
    public List<PlanResDto> getPlansByYearAndMonth(String memberId, Integer year, Integer month){
        var member = memberRepository.findById(memberId).orElseThrow(
                ()-> new NoSuchElementException("member not found")
        );
        var start = LocalDate.of(year, month, 1);
        var end = LocalDate.of(year, month, start.lengthOfMonth());
        LocalDateTime startLocalDateTime = start.atStartOfDay();
        LocalDateTime endLocalDateTime = end.atTime(LocalTime.MAX);

        var plans = planRepository.findPlanByMemberAndStartTimeBetween(member, startLocalDateTime, endLocalDateTime);
        List<PlanResDto> planResDtoList = new ArrayList<>();
        for(var x: plans){
            planResDtoList.add(new PlanResDto(x));
        }
        return planResDtoList;
    }
}

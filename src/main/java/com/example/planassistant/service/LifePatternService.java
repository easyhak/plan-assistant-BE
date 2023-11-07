package com.example.planassistant.service;

import com.example.planassistant.common.exception.NoSuchElementException;
import com.example.planassistant.domain.LifePattern;
import com.example.planassistant.domain.enumType.Life;
import com.example.planassistant.dto.lifepattern.LifePatternReqDto;
import com.example.planassistant.dto.lifepattern.LifePatternResDto;
import com.example.planassistant.repository.LifePatternRepository;
import com.example.planassistant.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LifePatternService {
    private final LifePatternRepository lifePatternRepository;
    private final MemberRepository memberRepository;

    // life pattern의 내용을 저장
    @Transactional
    public String createLifePattern(String memberId,LifePatternReqDto dto){
        var member = memberRepository.findById(memberId).orElseThrow(
                ()->new NoSuchElementException("member not found")
        );
        var lifePattern = new LifePattern(dto ,member);
        lifePatternRepository.save(lifePattern);
        return "created";
    }

    // life pattern의 내용을 업데이트
    @Transactional
    public String updateLifePattern(String memberId, LifePatternReqDto dto, Long lifePatternId){
        var member = memberRepository.findById(memberId).orElseThrow(
                ()->new NoSuchElementException("member not found")
        );
        var lifePattern = lifePatternRepository.findById(lifePatternId).orElseThrow(
                ()->new java.util.NoSuchElementException("life pattern not found")
        );
        /* dto 내용으로 변경*/
        lifePattern.setLife(dto.getLife());
        lifePattern.setDayOfTheWeek(dto.getDayOfTheWeek());
        lifePattern.setEndTime(dto.getEndTime());
        lifePattern.setStartTime(dto.getStartTime());
        return "updated";
    }

    // life를 보여준다.
    @Transactional(readOnly = true)
    public List<LifePatternResDto> getLifePatterns(String memberId, String life){
        var member = memberRepository.findById(memberId).orElseThrow(
                ()->new NoSuchElementException("member not found")
        );
        List<LifePattern> lifeList;
        if (life == null || life.isEmpty()){
            lifeList = lifePatternRepository.findByMember(member);
        }
        else {
            lifeList = lifePatternRepository.findByMemberAndLife(member, Life.valueOf(life));
        }
        ArrayList<LifePatternResDto> resDtoList = new ArrayList<>();
        for(LifePattern x: lifeList){
            resDtoList.add(new LifePatternResDto(x));
        }
        return resDtoList;
    }


}

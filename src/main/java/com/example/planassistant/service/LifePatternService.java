package com.example.planassistant.service;

import com.example.planassistant.common.exception.NoSuchElementException;
import com.example.planassistant.domain.LifePattern;
import com.example.planassistant.dto.lifepattern.LifePatternReqDto;
import com.example.planassistant.dto.lifepattern.LifePatternResDto;
import com.example.planassistant.repository.LifePatternRepository;
import com.example.planassistant.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LifePatternService {
    private final LifePatternRepository lifePatternRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String createLifePattern(String memberId,LifePatternReqDto dto){
        var member = memberRepository.findById(memberId).orElseThrow(
                ()->new NoSuchElementException("member not found")
        );

        lifePatternRepository.save(new LifePattern(dto ,member));
        return "created";
    }


    @Transactional(readOnly = true)
    public List<LifePatternResDto> getLifePatterns(String memberId){
        var member = memberRepository.findById(memberId).orElseThrow(
                ()->new NoSuchElementException("member not found")
        );
        var lifeList = lifePatternRepository.findByMember(member);
        ArrayList<LifePatternResDto> resDtoList = new ArrayList<>();
        for(LifePattern x: lifeList){
            resDtoList.add(new LifePatternResDto(x));
        }
        return resDtoList;
    }

}

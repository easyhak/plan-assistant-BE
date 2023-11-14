package com.example.planassistant.service;

import com.example.planassistant.domain.Importance;
import com.example.planassistant.dto.ImportanceReqDto;
import com.example.planassistant.dto.ImportanceResDto;
import com.example.planassistant.repository.ImportanceRepository;
import com.example.planassistant.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImportanceService {
    private final ImportanceRepository importanceRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public void updateImportance(String memberId, Long importanceId, ImportanceReqDto dto){
        var i = importanceRepository.findById(importanceId);
        var member = memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new NoSuchElementException("member not found")
                );
        var importance =  importanceRepository.findById(importanceId).orElseThrow(
                () -> new NoSuchElementException("importance not found")
        );

        importance.setDegree(dto.getDegree());
        importance.setName(dto.getName());
    }

    @Transactional(readOnly = true)
    public List<ImportanceResDto> getAllImportance(String memberId){
        var importanceList = importanceRepository.findByMember_Id(memberId);
        List<ImportanceResDto> importanceResDtos = new ArrayList<>();
        for(Importance x: importanceList){
            var i = ImportanceResDto.builder()
                    .name(x.getName())
                    .id(x.getId())
                    .degree(x.getDegree())
                    .build();
            importanceResDtos.add(i);
        }

        return importanceResDtos;
    }
}

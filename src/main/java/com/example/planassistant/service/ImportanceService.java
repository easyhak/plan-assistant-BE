package com.example.planassistant.service;

import com.example.planassistant.dto.ImportanceReqDto;
import com.example.planassistant.repository.ImportanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImportanceService {
    private final ImportanceRepository importanceRepository;

    public void saveImportance(String memberId, ImportanceReqDto dto){

    }
}

package com.example.planassistant.service;

import com.example.planassistant.domain.Importance;
import com.example.planassistant.domain.enumType.Thing;
import com.example.planassistant.repository.ImportanceRepository;
import com.example.planassistant.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImportanceService {
    private final ImportanceRepository importanceRepository;
    @Transactional
    public void updateImportance(String memberId, Thing name, Integer degree){
        var importance = importanceRepository.findByMember_IdAndName(memberId, name).orElseThrow(
                ()-> new NoSuchElementException("no such element")
            );
        importance.setDegree(degree);
    }


    @Transactional(readOnly = true)
    public Map<Thing ,Integer> getAllImportance(String memberId){
        var importanceList = importanceRepository.findByMember_Id(memberId);
        /*
        {
          "PRIORITY": 1,
          "NOT_FOCUS_TIME": 3,
          "DEADLINE": 5,
          "DISTANCE": 4,
          "FOCUS_TIME": 2
         }

         */
        var res = new HashMap<Thing ,Integer>();
        for(Importance x: importanceList){

            res.put(x.getName(), x.getDegree());
        }

        return res;
    }
}

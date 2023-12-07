package com.example.planassistant.service;

import com.example.planassistant.domain.Importance;
import com.example.planassistant.domain.enumType.Thing;
import com.example.planassistant.repository.ImportanceRepository;
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
        switch (degree) {
            case 1 -> importance.setWeight(0.1);
            case 2 -> importance.setWeight(0.15);
            case 3 -> importance.setWeight(0.2);
            case 4 -> importance.setWeight(0.25);
            case 5 -> importance.setWeight(0.3);
        }
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

    @Transactional(readOnly = true)
    public Map<Thing, Double> getWeight(String username) {
        var importanceList = importanceRepository.findByMember_Id(username);
        var res = new HashMap<Thing, Double>();
        for(Importance x: importanceList){
            res.put(x.getName(), x.getWeight());
        }

        return res;

    }

    @Transactional
    public void updateWeight(String username, Map<Thing, Double> req) {
        var importanceList = importanceRepository.findByMember_Id(username);
        // 5개 바꾸기
        var priority = importanceRepository.findByMember_IdAndName(username, Thing.PRIORITY).orElseThrow(
                ()-> new NoSuchElementException("no such element")
        );
        var focusTime = importanceRepository.findByMember_IdAndName(username, Thing.FOCUS_TIME).orElseThrow(
                ()-> new NoSuchElementException("no such element")
        );
        var notFocusTime = importanceRepository.findByMember_IdAndName(username, Thing.NOT_FOCUS_TIME).orElseThrow(
                ()-> new NoSuchElementException("no such element")
        );
        var distance = importanceRepository.findByMember_IdAndName(username, Thing.DISTANCE).orElseThrow(
                ()-> new NoSuchElementException("no such element")
        );
        var deadline = importanceRepository.findByMember_IdAndName(username, Thing.DEADLINE).orElseThrow(
                ()-> new NoSuchElementException("no such element")
        );

        priority.setWeight(req.get(Thing.PRIORITY));
        focusTime.setWeight(req.get(Thing.FOCUS_TIME));
        notFocusTime.setWeight(req.get(Thing.NOT_FOCUS_TIME));
        distance.setWeight(req.get(Thing.DISTANCE));
        deadline.setWeight(req.get(Thing.DEADLINE));


        log.info("weight updated");
        return;

    }
}

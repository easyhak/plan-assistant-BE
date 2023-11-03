package com.example.planassistant.service;



import com.example.planassistant.domain.Plan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanLogic {


    // 거리 계산 함수
    private ArrayList<Long> calculateDistance(List<Plan> plans, Long latitude, Long longitude){
        var distanceList = new ArrayList<Long>();
        for(Plan x: plans){
            var p = Math.abs(x.getLatitude() - latitude);
            var q = Math.abs(x.getLongitude() - longitude);

        }

        return null;
    }

    // 가중치 계산 함수
    
}

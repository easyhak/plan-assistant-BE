package com.example.planassistant.service;



import com.example.planassistant.domain.Plan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
            if (x.getLatitude() == 0 || x.getLongitude() == 0 || x.getLatitude() == null || x.getLongitude() == null){
                p = 0;
                q = 0;
            }
            distanceList.add(p+q);
        }

        return distanceList;
    }

    // 가중치 계산 함수
    private String calculatePriority(Integer priority, LocalDateTime deadline){
        return null;
    }
}

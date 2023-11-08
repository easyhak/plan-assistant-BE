package com.example.planassistant.controller;

import com.example.planassistant.common.CommonController;
import com.example.planassistant.service.PlanService;
import com.example.planassistant.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("mock")
@RequiredArgsConstructor
public class MockController extends CommonController {

    private final TodoService todoService;
    private final PlanService planService;

    /* todo -> plan 바꾼 값 보여주기
       plan과 todo 값을 미리 입력
       => 알고리즘 돌린 값을 봔환해주기 TodoPlan이라는 entity List 반환
    */

    @GetMapping
    public ResponseEntity getMockTodoData(){

        /*
        [

        ]
        startTime:
        endTime:
        place:
        priority:
        deadline:
        content:
        complete:
         */
        return OkReturn("good");
    }
}

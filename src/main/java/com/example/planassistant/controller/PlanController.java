package com.example.planassistant.controller;

import com.example.planassistant.common.CommonController;
import com.example.planassistant.dto.PlanReqDto;
import com.example.planassistant.service.PlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
@Slf4j
public class PlanController extends CommonController {

    private final PlanService planService;
    @PostMapping
    public ResponseEntity makePlan(@AuthenticationPrincipal User user, @RequestBody PlanReqDto planReqDto){
        planService.makePlan(user.getUsername(), planReqDto);
        return CreatedReturn("created");
    }

    @GetMapping("/{id}")
    public ResponseEntity getPlan(@AuthenticationPrincipal User user, @PathVariable Long id){
        return OkReturn(planService.getPlanById(id));
    }

    @GetMapping()
    public ResponseEntity getAllPlan(@AuthenticationPrincipal User user){
        return OkReturn(planService.getPlanByMember(user.getUsername()));
    }

    @PutMapping("/{id}")
    public ResponseEntity changePlan(@AuthenticationPrincipal User user, @PathVariable Long id, @RequestBody PlanReqDto planReqDto){
        planService.changePlan(id, planReqDto);
        return AcceptedReturn("changed");
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletePlan(@AuthenticationPrincipal User user, @PathVariable Long id){
        return AcceptedReturn(planService.deletePlan(id));
    }
}

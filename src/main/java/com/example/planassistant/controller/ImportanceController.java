package com.example.planassistant.controller;

import com.example.planassistant.common.CommonController;
import com.example.planassistant.domain.enumType.Thing;

import com.example.planassistant.service.ImportanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/importance")
@Tag(name = "Importance", description = "중요도 관련 API")
public class ImportanceController extends CommonController {
    private final ImportanceService importanceService;

    @Operation(description = "중요도 보여주기")
    @GetMapping
    public ResponseEntity getAllImportance(@AuthenticationPrincipal User user){
        log.info("getAllImportance Call");
        var x = importanceService.getAllImportance(user.getUsername());

        return OkReturn(x);
    }
    @Operation(description = "중요도 수정, Request Body 로는 정수형 하나만 들어옴")
    @PutMapping("/{name}")
    public ResponseEntity updateImportance(@AuthenticationPrincipal User user, @PathVariable Thing name, @RequestBody Integer degree){
        log.info("updateImportance call");
        importanceService.updateImportance(user.getUsername(), name, degree);
        return AcceptedReturn("updated");
    }

    @Operation(description = "가중치 가져오기" +
            "{\n" +
            "  \"DEADLINE\": 0.2,\n" +
            "  \"NOT_FOCUS_TIME\": 0.4,\n" +
            "  \"PRIORITY\": 0.1,\n" +
            "  \"DISTANCE\": 0.2,\n" +
            "  \"FOCUS_TIME\": 0.1\n" +
            "}")
    @GetMapping("/weight")
    public ResponseEntity getWeight(@AuthenticationPrincipal User user){
        log.info("getWeight call");
        var res = importanceService.getWeight(user.getUsername());
        return OkReturn(res);
    }

    @Operation(description = "가중치 수정, Request Body 로는 가중치 리스트 들어옴" +
            "{\n" +
            "  \"DEADLINE\": 0.2,\n" +
            "  \"NOT_FOCUS_TIME\": 0.4,\n" +
            "  \"PRIORITY\": 0.1,\n" +
            "  \"DISTANCE\": 0.2,\n" +
            "  \"FOCUS_TIME\": 0.1\n" +
            "}")
    @PutMapping("/weight")
    public ResponseEntity updateWeight(@AuthenticationPrincipal User user, @RequestBody Map<Thing, Double> req){
        log.info("updateWeight Call");
        importanceService.updateWeight(user.getUsername(), req);
        return AcceptedReturn("updated");
    }

}

package com.example.planassistant.controller;

import com.example.planassistant.common.CommonController;
import com.example.planassistant.dto.RecommendTodoReqDto;
import com.example.planassistant.service.RecommendTodoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend")
@Slf4j
@Tag(name = "Recommend Todo", description = "추천된 todo 관련 API")
public class RecommendTodoController extends CommonController {
    private final RecommendTodoService recommendTodoService;

    @PostMapping
    public ResponseEntity createRecommendTodos(@AuthenticationPrincipal User user, @RequestBody List<RecommendTodoReqDto> dtoList) {
        recommendTodoService.createRecommendTodos(user.getUsername(), dtoList);
        return CreatedReturn("created");
    }
    // query param 으로 시작과 끝 정할 수 있도록 하기
    @GetMapping
    public ResponseEntity getRecommendTodos(@AuthenticationPrincipal User user) {
        return OkReturn(recommendTodoService.getRecommendTodos(user.getUsername()));
    }
}

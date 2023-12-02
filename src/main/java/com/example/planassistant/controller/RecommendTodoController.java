package com.example.planassistant.controller;

import com.example.planassistant.common.CommonController;
import com.example.planassistant.dto.RecommendTodoReqDto;
import com.example.planassistant.dto.RecommendTodoResDto;
import com.example.planassistant.service.RecommendTodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        log.info("createRecommendTodos call");
        recommendTodoService.createRecommendTodos(user.getUsername(), dtoList);
        return CreatedReturn("created");
    }
    // query param 으로 시작과 끝 정할 수 있도록 하기

    public ResponseEntity getRecommendTodos(@AuthenticationPrincipal User user) {
        return OkReturn(recommendTodoService.getRecommendTodos(user.getUsername()));
    }

    @Operation(summary = "recommendTodo를 date로 받아서 보여줍니다. ", description = "startDate에 맞게 recommend todo 보여줍니다" +
            "yyyy-MM-dd 형식")
    @GetMapping("{date}")
    public ResponseEntity<List<RecommendTodoResDto>> getRecommendTodosByDate(@AuthenticationPrincipal User user, @PathVariable String date) {
        log.info("get recommend todo By StartDate Call " + date);
        LocalDate localDate = LocalDate.parse(date);
        return OkReturn(recommendTodoService.getRecommendTodosByDate(user.getUsername(), localDate));
    }

}

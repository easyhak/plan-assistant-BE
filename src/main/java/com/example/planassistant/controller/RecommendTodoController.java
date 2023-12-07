package com.example.planassistant.controller;

import com.example.planassistant.common.CommonController;
import com.example.planassistant.domain.Recommend;
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
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend")
@Slf4j
@Tag(name = "Recommend Todo", description = "추천된 todo 관련 API")
public class RecommendTodoController extends CommonController {
    private final RecommendTodoService recommendTodoService;

    @Operation(summary = "recommendTodo를 저장 또는 갱신합니다.", description = "recommendTodos 저장" +
            "list로 된 형태, 필요한 정보: id: {todo_id}, endTime: {yyyy-mm-dd HH:mm}, startTime: {yyyy-mm-dd}")
    @PostMapping
    public ResponseEntity createRecommendTodos(@AuthenticationPrincipal User user, @RequestBody List<RecommendTodoReqDto> dtoList) {
        log.info("createRecommendTodos call");
        recommendTodoService.createRecommendTodos(user.getUsername(), dtoList);
        return CreatedReturn("created");
    }


    @Operation(summary = "recommendTodo를 date로 받아서 보여줍니다. ", description = "startDate에 맞게 recommend todo 보여줍니다" +
            "yyyy-MM-dd 형식")
    @GetMapping("{date}")
    public ResponseEntity<List<RecommendTodoResDto>> getRecommendTodosByDate(@AuthenticationPrincipal User user, @PathVariable String date) {
        log.info("get recommend todo By StartDate Call " + date);
        LocalDate localDate = LocalDate.parse(date);
        return OkReturn(recommendTodoService.getRecommendTodosByDate(user.getUsername(), localDate));
    }


    @Operation(summary = "recommend 임시 테이블 저장", description = "recommend 값을 저장")
    @PostMapping("/table")
    public ResponseEntity<Recommend> saveRecommendTable(@AuthenticationPrincipal User user, @RequestBody Object recommend) {
        log.info("saveRecommendTable call");
        log.info(user.getUsername());
        System.out.println(recommend.toString());
        recommendTodoService.saveRecommend(user.getUsername(), recommend);
        return CreatedReturn("created");
    }

    @Operation(summary = "recommend 임시 테이블 가져옴", description = "recommend 값을 보여줌")
    @GetMapping("/table")
    public ResponseEntity<Recommend> getRecommendTable(@AuthenticationPrincipal User user) {
        log.info("getRecommendTable call");
        return OkReturn(recommendTodoService.getRecommend(user.getUsername()));
    }

    @DeleteMapping("/table")
    public ResponseEntity deleteRecommendTable(@AuthenticationPrincipal User user) {
        recommendTodoService.deleteRecommend(user.getUsername());
        return AcceptedReturn("deleted");
    }
}

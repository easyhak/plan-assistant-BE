package com.example.planassistant.controller;

import com.example.planassistant.common.CommonController;
import com.example.planassistant.dto.PlaceReqDto;
import com.example.planassistant.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Member", description = "Member 관련 API")
public class MemberController extends CommonController {
    private final MemberService memberService;

    @Operation(description = "집 장소 추가" +
            "{'place': '중앙대학교', 'longitude': 212.123, 'latitude':45.123 }")
    @PutMapping("/place")
    public ResponseEntity<String> insertPlace(@AuthenticationPrincipal User user, @RequestBody PlaceReqDto dto){
        log.info("insertPlace call");
        System.out.println(dto.toString());
        memberService.insertPlace(user.getUsername(), dto);
        return AcceptedReturn("updated");
    }
    @GetMapping
    public ResponseEntity getMember(@AuthenticationPrincipal User user){

        return OkReturn(memberService.getMember(user.getUsername()));
    }

    @Operation(description = "숫자 더하기")
    @PatchMapping
    public ResponseEntity addCount(@AuthenticationPrincipal User user){
        memberService.addCount(user.getUsername());
        return AcceptedReturn("updated");
    }
    @Operation(description = "숫자 가져오기")
    @GetMapping("/count")
    public ResponseEntity getCount(@AuthenticationPrincipal User user){
        return OkReturn(memberService.getCount(user.getUsername()));
    }

    @Operation(description = "todo, plan 전부 삭제")
    @DeleteMapping("/reset")
    public ResponseEntity deleteMember(@AuthenticationPrincipal User user){
        memberService.deletePlanAndTodoAndCategory(user.getUsername());
        return AcceptedReturn("deleted");
    }
}

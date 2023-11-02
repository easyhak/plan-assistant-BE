package com.example.planassistant.controller;

import com.example.planassistant.common.CommonController;
import com.example.planassistant.dto.lifepattern.LifePatternReqDto;
import com.example.planassistant.dto.lifepattern.LifePatternResDto;
import com.example.planassistant.service.LifePatternService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/life")
public class LifePatternController extends CommonController {
    private final LifePatternService lifePatternService;
    @PostMapping
    public ResponseEntity createLifePattern(@AuthenticationPrincipal User user, @RequestBody LifePatternReqDto dto){
        lifePatternService.createLifePattern(user.getUsername(), dto);
        return CreatedReturn("created");
    }

    @GetMapping
    public ResponseEntity getLifePatterns(@AuthenticationPrincipal User user){
        return OkReturn(lifePatternService.getLifePatterns(user.getUsername()));
    }
}

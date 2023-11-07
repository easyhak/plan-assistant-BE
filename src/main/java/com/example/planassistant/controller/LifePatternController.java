package com.example.planassistant.controller;

import com.example.planassistant.common.CommonController;
import com.example.planassistant.dto.lifepattern.LifePatternReqDto;
import com.example.planassistant.service.LifePatternService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/life")
@Tag(name = "LifePattern", description = "생활패턴 관련 API")
public class LifePatternController extends CommonController {
    private final LifePatternService lifePatternService;
    @PostMapping
    public ResponseEntity createLifePattern(@AuthenticationPrincipal User user, @RequestBody LifePatternReqDto dto){
        lifePatternService.createLifePattern(user.getUsername(), dto);
        return CreatedReturn("created");
    }

    @GetMapping
    public ResponseEntity getLifePatterns(@AuthenticationPrincipal User user, @RequestParam(required = false) String life){
        return OkReturn(lifePatternService.getLifePatterns(user.getUsername(), life));
    }
}

package com.example.planassistant.controller;

import com.example.planassistant.common.CommonController;
import com.example.planassistant.dto.ImportanceReqDto;
import com.example.planassistant.dto.ImportanceResDto;
import com.example.planassistant.repository.ImportanceRepository;
import com.example.planassistant.service.ImportanceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/importance")
@Tag(name = "Importance", description = "중요도를 관련 API")
public class ImportanceController extends CommonController {
    private final ImportanceService importanceService;

    @GetMapping
    public ResponseEntity getAllImportance(@AuthenticationPrincipal User user){
        log.info("getAllImportance Call");
        var x = importanceService.getAllImportance(user.getUsername());

        return OkReturn(x);
    }
    @PutMapping("/{id}")
    public ResponseEntity updateImportance(@AuthenticationPrincipal User user, @PathVariable Long id, @RequestBody ImportanceReqDto dto){
        log.info("updateImportance call");
        importanceService.updateImportance(user.getUsername(), id, dto);
        return AcceptedReturn("updated");
    }
}

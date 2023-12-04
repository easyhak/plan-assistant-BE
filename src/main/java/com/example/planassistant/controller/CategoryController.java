package com.example.planassistant.controller;

import com.example.planassistant.common.CommonController;
import com.example.planassistant.dto.CategoryReqDto;
import com.example.planassistant.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Category", description = "Category 관련 API")
public class CategoryController extends CommonController {
    private final CategoryService categoryService;

    @Operation(summary = "category 생성", description = "category를 생성 해줍니다.")
    @ApiResponse(responseCode = "201", description = "category 생성 성공", content = @Content(schema = @Schema(implementation = CategoryReqDto.class)))
    @PostMapping
    public ResponseEntity createCategory(@AuthenticationPrincipal User user, @RequestBody CategoryReqDto categoryReqDto){
        log.info(categoryReqDto.toString());
        categoryService.createCategory(user.getUsername(), categoryReqDto);
        return CreatedReturn("created");
    }

    @Operation(summary = "category 상세 조회", description = "category 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity getCategory(@AuthenticationPrincipal User user, @PathVariable Long id) {
        log.info("getCategory call");
//        return OkReturn(categoryService.getCategoryById(id));
        return OkReturn("ok");
    }

    @Operation(summary = "category 전체 조회", description = "category 전체 조회")
    @GetMapping
    public ResponseEntity getCategories(@AuthenticationPrincipal User user) {
        log.info("getCategories call");
        return OkReturn(categoryService.getCategories(user.getUsername()));
    }
}

package com.example.planassistant.dto;

import com.example.planassistant.domain.enumType.Thing;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Importance Req DTO", description = "중요도 요청 DTO")
public class ImportanceReqDto {
    @Schema(description = "이름", example = "FOCUS_TIME")
    private Thing name;
    @Schema(description = "정도", example = "3")
    private Integer degree;
}

package com.example.planassistant.dto;

import com.example.planassistant.domain.enumType.Thing;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Schema(description = "Importance Res Dto")
@ToString
@Getter
public class ImportanceResDto {
    @Schema(description = "Id",  example = "1")

    private Long id;
    @Schema(description = "이름",  example = "PRIORITY")
    private Thing name;

    @Schema(description = "정도", example = "3")
    private Integer degree;
    @Schema(description = "가중치", example = "0.2")
    private Double weight;
    @Builder
    public ImportanceResDto(Long id, Thing name, Integer degree, Double weight){
        this.id = id;
        this.name = name;
        this.degree = degree;
        this.weight = weight;
    }
}

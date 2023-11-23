package com.example.planassistant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
@Schema(description = "Place Response Dto")
public class PlaceReqDto {
    private Double longitude;
    private Double latitude;
    private String place;
}

package com.example.planassistant.dto;

import com.example.planassistant.domain.enumType.Thing;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImportanceReqDto {
    private Thing name;

    private Integer degree;
}

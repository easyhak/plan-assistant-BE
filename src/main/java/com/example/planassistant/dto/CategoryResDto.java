package com.example.planassistant.dto;

import lombok.Getter;

@Getter
public class CategoryResDto {
    private String name;

    public CategoryResDto(String name) {
        this.name = name;
    }
}

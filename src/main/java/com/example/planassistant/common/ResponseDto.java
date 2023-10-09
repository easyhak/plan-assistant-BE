package com.example.planassistant.common;

import com.example.planassistant.common.code.ApiCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDto<T> {
    private int statusCode;
    private String codeMsg;
    private T data;
}

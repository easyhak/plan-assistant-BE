package com.example.planassistant.common.exception;

import com.example.planassistant.common.code.ApiCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Exception{
    private ApiCode status;
}

package com.example.planassistant.common;

import com.example.planassistant.common.code.ApiCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class CommonController  {

    public ResponseEntity SuccessReturn(Object data) {
        return ResponseEntity.ok().body(
                ResponseDto.builder().statusCode(ApiCode.SUCCESS.getCode()).codeMsg(ApiCode.SUCCESS.getMsg()).data(data).build());
    }

    public ResponseEntity SuccessReturn() {
        return ResponseEntity.ok().body(
                ResponseDto.builder().statusCode(ApiCode.SUCCESS.getCode()).codeMsg(ApiCode.SUCCESS.getMsg()).build());
    }

    public ResponseEntity ErrorReturn(ApiCode apiCode) {
        return ResponseEntity.ok().body(
                ResponseDto.builder().statusCode(apiCode.getCode()).codeMsg(apiCode.getMsg()).build());
    }

    public ResponseEntity ErrorReturn(ApiCode apiCode, Object data) {
        return ResponseEntity.ok().body(
                ResponseDto.builder().statusCode(apiCode.getCode()).codeMsg(apiCode.getMsg()).data(data).build());
    }

    public ResponseEntity ErrorReturn(int code, String msg) {
        return ResponseEntity.ok().body(
                ResponseDto.builder().statusCode(code).codeMsg(msg).build());
    }

    public ResponseEntity ErrorReturn(int code, String msg, Object data) {
        return ResponseEntity.ok().body(
                ResponseDto.builder().statusCode(code).codeMsg(msg).data(data).build());
    }
}

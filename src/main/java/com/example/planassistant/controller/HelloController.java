package com.example.planassistant.controller;

import com.example.planassistant.common.CommonController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController extends CommonController {

    @GetMapping("/hello")
    public ResponseEntity HelloWorld(){
        return OkReturn("Hello World");

    }
}

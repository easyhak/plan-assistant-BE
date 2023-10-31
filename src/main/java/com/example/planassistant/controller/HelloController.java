package com.example.planassistant.controller;

import com.example.planassistant.common.CommonController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Hello", description = "Example")
public class HelloController extends CommonController {

    @GetMapping("/hello")
    public ResponseEntity HelloWorld(){
        return OkReturn("Hello World");

    }
}

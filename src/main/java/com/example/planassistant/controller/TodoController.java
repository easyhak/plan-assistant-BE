package com.example.planassistant.controller;


import com.example.planassistant.common.CommonController;
import com.example.planassistant.dto.TodoReqDto;
import com.example.planassistant.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController extends CommonController {
    private final TodoService todoService;

    @PostMapping()
    public ResponseEntity makeTodo(@AuthenticationPrincipal User user, @RequestBody TodoReqDto todoReqDto){
        log.info("todo  " + todoReqDto.toString());
        todoService.addTodo(todoReqDto, user.getUsername());
        return CreatedReturn("created");
    }
    @GetMapping()
    public ResponseEntity getAllTodo(@AuthenticationPrincipal User user){
        var res = todoService.getAllTodo((user.getUsername()));
        return OkReturn(res);
    }
    @GetMapping("{id}")
    public ResponseEntity getTodo(@AuthenticationPrincipal User user, @PathVariable Long id){
        return OkReturn(todoService.getTodo(id));
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return OkReturn("deleted");
    }
}

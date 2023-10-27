package com.example.planassistant.service;

import com.example.planassistant.domain.Todo;
import com.example.planassistant.dto.TodoReqDto;
import com.example.planassistant.common.exception.MemberNotFoundException;
import com.example.planassistant.repository.MemberRepository;
import com.example.planassistant.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodoService {
    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;
    @Transactional(readOnly = true)
    public void AddTodo(TodoReqDto todoReqDto, Long memberId){
        var member = memberRepository.findById(memberId)
                .orElseThrow(()->new MemberNotFoundException());
        Todo.builder()
                .priority(todoReqDto.getPriority())
                .place(todoReqDto.getPlace())
                .deadline(todoReqDto.getDeadline())
                .member(member)
                .build();
    }
}

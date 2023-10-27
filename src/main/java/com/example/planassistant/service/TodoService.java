package com.example.planassistant.service;

import com.example.planassistant.domain.Todo;
import com.example.planassistant.dto.TodoReqDto;
import com.example.planassistant.dto.TodoResDto;
import com.example.planassistant.repository.MemberRepository;
import com.example.planassistant.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodoService {
    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;

    // todo 저장
    @Transactional
    public void addTodo(TodoReqDto todoReqDto, String memberId){
        log.info("todo의 정보: " + todoReqDto.toString());
        var member = memberRepository.findById(memberId)
                .orElseThrow(()->new NoSuchElementException("cannot find member"));
        

        var todo = Todo.builder()
                .priority(todoReqDto.getPriority())
                .place(todoReqDto.getPlace())
                .deadline(todoReqDto.getDeadline())
                .content(todoReqDto.getContent())
                .latitude(todoReqDto.getLongitude())
                .longitude(todoReqDto.getLongitude())
                .member(member)
                .build();

        todoRepository.save(todo);
    }

    // 멤버의 모든 todo 가져오기
    @Transactional(readOnly = true)
    public List<TodoResDto> getAllTodo(String memberId){
        var member = memberRepository.findById(memberId)
                .orElseThrow(()-> new NoSuchElementException("cannot find member"));

        var todos = todoRepository.findTodoByMemberOrderByUpdateDate(member);
        List<TodoResDto> todoResDtoList = new ArrayList<>();
        for(Todo x: todos){
            todoResDtoList.add(new TodoResDto(x));
        }
        return todoResDtoList;
    }

    // 특정 todo 가져오기
    @Transactional(readOnly = true)
    public TodoResDto getTodo(Long todoId){
        var todo =todoRepository.findById(todoId).orElseThrow(
                ()->new NoSuchElementException("cannot find todo"));


        
        return new TodoResDto(todo);
    }

    public void deleteTodo(Long id) {

        todoRepository.deleteById(id);
    }
}

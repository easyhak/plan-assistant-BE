package com.example.planassistant.service;

import com.example.planassistant.domain.RecommendTodo;
import com.example.planassistant.dto.RecommendTodoReqDto;
import com.example.planassistant.dto.RecommendTodoResDto;
import com.example.planassistant.repository.RecommendTodoRepository;
import com.example.planassistant.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecommendTodoService {
    private final RecommendTodoRepository recommendTodoRepository;
    private final TodoRepository todoRepository;

    @Transactional
    public void createRecommendTodos(String memberId, List<RecommendTodoReqDto> dtoList) {
        List<RecommendTodo> recommendTodos = new ArrayList<>();
        for(RecommendTodoReqDto dto: dtoList){
            var todo =  todoRepository.findById(dto.getId()).orElseThrow(
                    () -> new NoSuchElementException("todo not exist")
            );

            recommendTodos.add(dto.toEntity(dto,todo));
        }
        recommendTodoRepository.saveAll(recommendTodos);
    }

    public List<RecommendTodoResDto> getRecommendTodos(String memberId) {
        List<RecommendTodo> recommendTodos = recommendTodoRepository.findAll();
        List<RecommendTodoResDto> resDtos = new ArrayList<>();
        for(RecommendTodo todo: recommendTodos){
            resDtos.add(new RecommendTodoResDto(todo));
        }
        return resDtos;
    }
}

package com.example.planassistant.service;

import com.example.planassistant.domain.Recommend;
import com.example.planassistant.domain.RecommendTodo;
import com.example.planassistant.dto.RecommendTodoReqDto;
import com.example.planassistant.dto.RecommendTodoResDto;
import com.example.planassistant.repository.mongodb.RecommendTableRepository;
import com.example.planassistant.repository.mysql.MemberRepository;
import com.example.planassistant.repository.mysql.RecommendTodoRepository;
import com.example.planassistant.repository.mysql.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecommendTodoService {
    private final RecommendTodoRepository recommendTodoRepository;
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;
    // mongodb
    private final RecommendTableRepository recommendTableRepository;
    @Transactional
    public void createRecommendTodos(String memberId, List<RecommendTodoReqDto> dtoList) {
        List<RecommendTodo> recommendTodos = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for(RecommendTodoReqDto dto: dtoList){
            // find
            var recommendTodo = recommendTodoRepository.findByTodo_Id(dto.getId()).orElse(null);
            if (recommendTodo == null){
                var todo = todoRepository.findById(dto.getId()).orElseThrow(
                        () -> new NoSuchElementException("todo not exist")
                );
                // make RecommendTodo
                recommendTodos.add(dto.toEntity(dto,todo));
            }
            else {
                var start = LocalDateTime.parse(dto.getStartTime(), formatter);
                var end = LocalDateTime.parse(dto.getEndTime(), formatter);
                recommendTodo.update(start, end);
            }
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
    @Transactional(readOnly = true)
    public List<RecommendTodoResDto> getRecommendTodosByDate(String memberId, LocalDate startDate) {

        var member = memberRepository.findById(memberId).orElseThrow(
                () -> new NoSuchElementException("member not exist")
        );

        LocalDateTime start = startDate.atStartOfDay(); // 2021-10-25 00:00:00.00000000
        LocalDateTime end = startDate.atTime(LocalTime.MAX);
        var recommendTodosByDate = recommendTodoRepository.findRecommendTodoByStartTimeBetweenOrderByStartTime(start, end);

        List<RecommendTodoResDto> resDtos = new ArrayList<>();
        for (RecommendTodo x: recommendTodosByDate){
            // 같은 멤버면 추가
            if (x.getTodo().getMember().equals(member))
                resDtos.add(new RecommendTodoResDto(x));
        }

        return resDtos;
    }

    @Transactional
    public String updateRecommendTodo(Long id, RecommendTodoReqDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        var recommendTodo = recommendTodoRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("recommendTodo not exist")
        );
        var start = LocalDateTime.parse(dto.getStartTime(), formatter);
        var end = LocalDateTime.parse(dto.getEndTime(), formatter);
        recommendTodo.update(start, end);
        return "success";
    }

    @Transactional
    public String deleteRecommendTodo(Long id) {
        recommendTodoRepository.deleteById(id);
        return "success";  // 삭제 성공 시 응답 메시지 반환 필요. 예시로 "success"
    }

    // 임시 recommend 저장
    @Transactional
    public void saveRecommend(String memberId, Object recommend) {
        var recommendTable = recommendTableRepository.findRecommendByMemberId(memberId).orElse(null);
        System.out.println(recommendTable);
        if (recommendTable != null){
            // null이 아니면 삭제
            log.info("recommend table is not null");
            recommendTableRepository.deleteRecommendByMemberId(memberId);
            log.info("delete recommend");
        }
        var x =  new Recommend(memberId, recommend);
        log.info(x.toString());
        recommendTableRepository.save(x);
        log.info("save recommend");
    }

    // 임시 recommend 가져오기
    @Transactional(readOnly = true)
    public Recommend getRecommend(String userId) {
        return recommendTableRepository.findRecommendByMemberId(userId).orElse(null); // 없으면 null 반환 가능. 없으면 예외 발생. 있으면 응�
    }

    @Transactional
    public void deleteRecommend(String userId) {
        var x = recommendTableRepository.findRecommendByMemberId(userId).orElseThrow(
                () -> new NoSuchElementException("recommend not exist")
        );
        recommendTableRepository.deleteRecommendByMemberId(userId);
    }
}

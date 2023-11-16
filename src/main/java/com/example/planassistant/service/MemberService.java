package com.example.planassistant.service;

import com.example.planassistant.common.exception.NoSuchElementException;
import com.example.planassistant.dto.MemberResDto;
import com.example.planassistant.dto.MemberResponseDto;
import com.example.planassistant.dto.PlaceReqDto;
import com.example.planassistant.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void insertPlace(String memberId, PlaceReqDto dto){
        var member =  memberRepository.findById(memberId).orElseThrow(
                ()->new NoSuchElementException("member not found")
        );
        member.updatePlace(dto.getPlace(), dto.getLatitude(), dto.getLongitude());
    }

    public MemberResDto getMember(String username) {
        var member = memberRepository.findById(username).orElseThrow(
                ()->new NoSuchElementException("member not found")
        );
        return new MemberResDto(member);
    }
}

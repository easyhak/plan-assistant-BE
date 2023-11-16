package com.example.planassistant.dto;

import com.example.planassistant.domain.Authority;
import com.example.planassistant.domain.Importance;
import com.example.planassistant.domain.LifePattern;
import com.example.planassistant.domain.Member;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class MemberResDto {
    private String id;

    private String email;


    private String place;
    private Double latitude;
    private Double longitude;
    private Authority authority;

    public MemberResDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.place = member.getPlace();
        this.latitude = member.getLatitude();
        this.longitude = member.getLongitude();
        this.authority = member.getAuthority();

    }
}

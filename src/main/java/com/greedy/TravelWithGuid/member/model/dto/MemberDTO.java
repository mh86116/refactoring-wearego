package com.greedy.TravelWithGuid.member.model.dto;

import com.greedy.TravelWithGuid.member.model.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberDTO {
    private Long no;
    private String authority;
    private String email;
    private String nickname;
    private String phone;
    private boolean isEnable;
    private LocalDateTime createdDt;

    @QueryProjection
    public MemberDTO(Member member) {
        this.no = member.getId();
        this.authority = member.getAuthority();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.phone = member.getPhone();
        this.isEnable = member.isEnable();
        this.createdDt = member.getCreatedDt();
    }
}

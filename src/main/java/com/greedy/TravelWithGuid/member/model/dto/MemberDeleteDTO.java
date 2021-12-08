package com.greedy.TravelWithGuid.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDeleteDTO {
    private String email;
    private String pwd;
    private String reason;
}

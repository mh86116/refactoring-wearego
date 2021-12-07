package com.greedy.TravelWithGuid.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberCheckDTO {
    private Long id;
    private String email;
    private String pwd;
}

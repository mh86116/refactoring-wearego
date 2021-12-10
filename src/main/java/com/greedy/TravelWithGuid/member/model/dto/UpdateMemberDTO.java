package com.greedy.TravelWithGuid.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateMemberDTO {
    private String nickname;
    private String phone;
}

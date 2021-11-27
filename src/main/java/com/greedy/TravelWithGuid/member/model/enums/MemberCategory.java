package com.greedy.TravelWithGuid.member.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberCategory {
    AUTHORITY("AUTHORITY"),
    NICKNAME("NICKNAME"),
    PHONE("PHONE");

    private String value;
}

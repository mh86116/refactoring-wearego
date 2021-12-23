package com.greedy.TravelWithGuid.guide.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Approval {
    SUBMIT("대기"),
    APPROVE("승인"),
    REJECT("반려");

    private String value;
}

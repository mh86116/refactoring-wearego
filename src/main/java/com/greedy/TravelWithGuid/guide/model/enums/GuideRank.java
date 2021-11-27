package com.greedy.TravelWithGuid.guide.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GuideRank {
    SILVER("SILVER"),
    GOLD("GOLD"),
    VIP("VIP");

    private String value;
}

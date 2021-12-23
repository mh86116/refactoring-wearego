package com.greedy.TravelWithGuid.guide.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    GUIDE("GUIDE"),
    GOODS("GOODS");

    private String vaule;
}

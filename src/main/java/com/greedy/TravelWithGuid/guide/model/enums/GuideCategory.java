package com.greedy.TravelWithGuid.guide.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GuideCategory {
    EMAIL("EMAIL"),
    BANK("BANK"),
    ACCOUNT("ACCOUNT"),
    RANK("RANK"),
    WARNING("WARNING"),
    APPROVAL("APPROVAL");

    private String value;
}

package com.greedy.TravelWithGuid.guide.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GuideCategory {
    NAME("NAME"),
    EMAIL("EMAIL"),
    BANK("BANK"),
    ACCOUNT("ACCOUNT"),
    RANK("RANK"),
    WARNING("WARNING");


    private String value;
}

package com.greedy.TravelWithGuid.guide.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Warning {
    YELLOW("YELLOW"),
    ORANGE("ORANGE"),
    RED("RED");

    private String value;
}

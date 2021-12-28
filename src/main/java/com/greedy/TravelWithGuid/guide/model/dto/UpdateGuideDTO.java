package com.greedy.TravelWithGuid.guide.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateGuideDTO {
    private String email;
    private String bank;
    private String account;
}

package com.greedy.TravelWithGuid.guide.model.dto;

import com.greedy.TravelWithGuid.guide.model.enums.GuideCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class EditGuideDTO {
    private String name;
    private String email;
    private String bank;
    private String account;
    private String intro;

    List<GuideCategory> categories;
    List<EditGuideDTO> dtos;

}

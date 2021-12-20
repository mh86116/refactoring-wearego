package com.greedy.TravelWithGuid.guide.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class EditGoodsDTO {
    private String email;
    private String title;
    private String price;
    private String place;
    private String startDt;
    private String endDt;
    private String person;
    private String body;

//    private List<OptionDTO> optionDTOList;
}

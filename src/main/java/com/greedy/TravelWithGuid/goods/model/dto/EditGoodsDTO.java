package com.greedy.TravelWithGuid.goods.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
}

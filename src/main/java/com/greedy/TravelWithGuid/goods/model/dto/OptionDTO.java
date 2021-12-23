package com.greedy.TravelWithGuid.goods.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class OptionDTO implements Serializable {
    private String optionName;
    private String optionPrice;
    private int goodsNo;
}

package com.greedy.TravelWithGuid.guide.model.dto;

import com.greedy.TravelWithGuid.guide.model.entity.Goods;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class GoodsDTO {
    private Long id;
    private String title;
    private Integer price;
    private String place;
    private LocalDate startDt;
    private LocalDate endDt;
    private Integer person;
    private String body;
    private boolean isEnable;
    private String imgUrl;
    private String optionName;
    private String optionPrice;
    private LocalDateTime createdDt;

    @QueryProjection
    public GoodsDTO(Goods goods) {
        this.id = goods.getId();
        this.title = goods.getTitle();
        this.price = goods.getPrice();
        this.place = goods.getPlace();
        this.startDt = goods.getStartDt();
        this.endDt = goods.getEndDt();
        this.person = goods.getPerson();
        this.body = goods.getBody();
        this.isEnable = goods.isEnable();
        this.imgUrl = goods.getAttachments().get(0).getSavePath();
        this.optionName = goods.getOptions().get(0).getOptionName();
        this.optionPrice = goods.getOptions().get(0).getOptionPrice();
        this.createdDt = goods.getCreatedDt();
    }

}

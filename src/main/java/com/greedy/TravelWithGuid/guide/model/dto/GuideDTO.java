package com.greedy.TravelWithGuid.guide.model.dto;

import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GuideDTO {
    private Long id;
    private String name;
    private String email;
    private String bank;
    private String account;
    private String intro;
    private String rank;
    private LocalDateTime createdDt;
    private String warning;
    private boolean isEnable;
    private String imgUrl;

    @QueryProjection
    public GuideDTO(Guide guide) {
        this.id = guide.getId();
        this.name = guide.getName();
        this.email = guide.getEmail();
        this.bank = guide.getBank();
        this.account = guide.getAccount();
        this.intro = guide.getIntro();
        this.rank = String.valueOf(guide.getRank());
        this.warning = String.valueOf(guide.getWarning());
        this.createdDt = guide.getCreatedDt();
        this.isEnable = guide.isEnable();
        this.imgUrl = guide.getAttachments().get(0).getSavePath();
    }

}

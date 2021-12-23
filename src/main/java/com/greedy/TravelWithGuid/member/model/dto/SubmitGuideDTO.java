package com.greedy.TravelWithGuid.member.model.dto;

import com.greedy.TravelWithGuid.guide.model.entity.Examine;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SubmitGuideDTO {
    private Long id;
    private String nickname;
    private String imgUrl;
    private String approval;
    private String name;
    private String email;
    private String bank;
    private String account;
    private String intro;
    private LocalDateTime createdDt;

    @QueryProjection
    public SubmitGuideDTO(Examine entity) {
        this.id = entity.getId();
        this.nickname = entity.getMember().getNickname();
        this.name = entity.getGuide().getName();
        this.email = entity.getGuide().getEmail();
        this.bank = entity.getGuide().getBank();
        this.account = entity.getGuide().getAccount();
        this.intro = entity.getGuide().getIntro();
        this.createdDt = entity.getCreatedDt();
        this.imgUrl = entity.getGuide().getGuideAttachments().get(0).getSavePath();
    }
}

package com.greedy.TravelWithGuid.member.model.dto;

import com.greedy.TravelWithGuid.member.model.entity.GuideApproval;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RejectGuideDTO {
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
    private String reason;
    private LocalDateTime updateDt;

    @QueryProjection
    public RejectGuideDTO(GuideApproval entity) {
        this.id = entity.getId();
        this.nickname = entity.getMember().getNickname();
        this.name = entity.getGuide().getName();
        this.email = entity.getGuide().getEmail();
        this.bank = entity.getGuide().getBank();
        this.account = entity.getGuide().getAccount();
        this.intro = entity.getGuide().getIntro();
        this.createdDt = entity.getCreatedDt();
        this.approval = entity.getApproval().getValue();
        this.imgUrl = entity.getGuide().getAttachments().get(0).getSavePath();
        this.reason = entity.getReject();
        this.updateDt = entity.getUpdateDate();

    }

}

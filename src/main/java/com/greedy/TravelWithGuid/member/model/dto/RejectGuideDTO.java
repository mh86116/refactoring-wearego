package com.greedy.TravelWithGuid.member.model.dto;

import com.greedy.TravelWithGuid.guide.model.entity.Examine;
import com.greedy.TravelWithGuid.guide.model.enums.Approval;
import com.greedy.TravelWithGuid.guide.model.enums.Category;
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
    private Approval examine;
    private LocalDateTime createdDt;
    private String reason;
    private LocalDateTime updateDt;

    @QueryProjection
    public RejectGuideDTO(Examine entity) {
        this.id = entity.getId();
        this.nickname = entity.getMember().getNickname();
        this.name = entity.getGuide().getName();
        this.email = entity.getGuide().getEmail();
        this.bank = entity.getGuide().getBank();
        this.account = entity.getGuide().getAccount();
        this.intro = entity.getGuide().getIntro();
        this.createdDt = entity.getCreatedDt();
        this.approval = entity.getApproval().getValue();
        this.imgUrl = entity.getGuide().getGuideAttachments().get(0).getSavePath();
        this.examine = entity.getApproval();
        this.reason = entity.getReject();
        this.updateDt = entity.getUpdateDt();
    }
}

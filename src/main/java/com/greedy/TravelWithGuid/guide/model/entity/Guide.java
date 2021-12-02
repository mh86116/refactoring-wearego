package com.greedy.TravelWithGuid.guide.model.entity;

import com.greedy.TravelWithGuid.cmmn.model.entity.BaseTimeRegisterEntity;
import com.greedy.TravelWithGuid.guide.model.enums.GuideRank;
import com.greedy.TravelWithGuid.guide.model.enums.Warning;
import com.greedy.TravelWithGuid.member.model.entity.Member;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "GUIDE")
public class Guide extends BaseTimeRegisterEntity implements Persistable<Long> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GUIDE_NO")
    private Long id;

    @OneToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @Column(name = "GUIDE_NAME")
    private String name;

    @Column(name = "GUIDE_EMAIL")
    private String email;

    @Column(name = "GUIDE_BANK")
    private String bank;

    @Column(name = "GUIDE_ACCOUNT")
    private String account;

    @Column(name = "GUIDE_INTRO", length = 4000)
    private String intro;

    @Enumerated(EnumType.STRING)
    @Column(name = "GUIDE_RANK")
    private GuideRank rank;

    @Enumerated(EnumType.STRING)
    @Column(name = "GUIDE_WARNING")
    private Warning warning;

    @Column(name = "GUIDE_APPROVE")
    private boolean approvalYn;

    @Column(name = "IS_ENABLE")
    private boolean isEnable;

    public static Guide createGuide(String name, String email, String bank, String account, String intro, Member member, boolean approvalYn, boolean isEnable) {
        return Guide.builder()
                .name(name)
                .email(email)
                .bank(bank.replace(" ", ""))
                .account(account.replace(" ", ""))
                .intro(intro)
                .member(member)
                .approvalYn(approvalYn)
                .isEnable(isEnable)
                .build();
    }

    public void patchGuide(Long id) {
        this.id = id;
        this.rank = GuideRank.SILVER;
        this.warning = Warning.BASIC;
        this.approvalYn = true;
        this.isEnable = true;
    }

    @Override
    public boolean isNew() { return getCreatedDt() == null; }
}

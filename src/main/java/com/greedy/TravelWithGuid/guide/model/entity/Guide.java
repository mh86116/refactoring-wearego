package com.greedy.TravelWithGuid.guide.model.entity;

import com.greedy.TravelWithGuid.cmmn.model.entity.BaseTimeRegisterEntity;
import com.greedy.TravelWithGuid.guide.model.enums.GuideRank;
import com.greedy.TravelWithGuid.guide.model.enums.Warning;
import com.greedy.TravelWithGuid.member.model.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Getter
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

    @Override
    public boolean isNew() { return getCreatedDt() == null; }
}

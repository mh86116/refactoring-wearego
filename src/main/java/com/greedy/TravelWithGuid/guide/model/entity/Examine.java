package com.greedy.TravelWithGuid.guide.model.entity;

import com.greedy.TravelWithGuid.cmmn.model.entity.BaseEntity;
import com.greedy.TravelWithGuid.guide.model.enums.Approval;
import com.greedy.TravelWithGuid.guide.model.enums.Category;
import com.greedy.TravelWithGuid.member.model.entity.Member;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Examine extends BaseEntity implements Persistable<Long> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "Member_NO")
    private Member member;

    @OneToOne
    @JoinColumn(name = "GUIDE_NO")
    private Guide guide;

    @Enumerated(STRING)
    @Column(name = "CATEGORY")
    private Category category;

    @Column(name = "APPROVAL")
    private Approval approval;

    @Column(name = "REJECT", length = 4000)
    private String reject;

    public static Examine createGuide(Member member, Guide guide, Approval value) {
        return Examine.builder()
                .member(member)
                .guide(guide)
                .approval(value)
                .build();
}

    @Override
    public boolean isNew() {
        return getCreatedDt() == null;
    }

    public void patchApprove(Long id, Approval approve) {
        this.id = id;
        this.approval = (approve != null) ? approve : this.approval;
    }

    public void patchReject(Long id, Approval reject, String rejectValue) {
        this.id = id;
        this.approval = (reject != null) ? reject : this.approval;
        this.reject = rejectValue;

    }
}

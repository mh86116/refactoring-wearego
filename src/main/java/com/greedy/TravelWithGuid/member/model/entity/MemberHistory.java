package com.greedy.TravelWithGuid.member.model.entity;

import com.greedy.TravelWithGuid.cmmn.model.entity.BaseTimeModifyEntity;
import com.greedy.TravelWithGuid.member.model.enums.MemberCategory;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity @Getter
@Builder @ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBER_HISTORY")
public class MemberHistory extends BaseTimeModifyEntity implements Persistable<Long> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_NO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @Column(name = "CATEGORY")
    private MemberCategory category;

    @Column(name = "BEFORE_VALUE")
    private String beforeValue;

    @Column(name = "AFTER_VALUE")
    private String afterValue;

    @Override
    public boolean isNew() {
        return getUpdateDt() == null;
    }
}

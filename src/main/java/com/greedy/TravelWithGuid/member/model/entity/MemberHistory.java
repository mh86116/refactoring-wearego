package com.greedy.TravelWithGuid.member.model.entity;

import com.greedy.TravelWithGuid.cmmn.model.entity.BaseEntity;
import com.greedy.TravelWithGuid.member.model.enums.MemberCategory;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity @Getter
@Builder @ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBER_HISTORY")
public class MemberHistory extends BaseEntity implements Persistable<Long> {
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

    @Column(name = "REASON")
    private String reason;


    public static MemberHistory updateMember(Member id, String value, MemberCategory type, String before) {
        MemberHistory history = new MemberHistory();
        history.member = id;
        history.beforeValue = (!before.equals(history.getBeforeValue())) ? before : null;
        history.category = (type != null) ? type : history.category;
        history.afterValue = (!value.equals(history.getBeforeValue())) ? value : history.getAfterValue();
        return history;
    }

    public static MemberHistory delete(Member id, String value, String reason) {
        MemberHistory history = new MemberHistory();
        history.member = id;
        history.afterValue = value;
        history.reason = reason;
        return history;
    }

    @Override
    public boolean isNew() {
        return getUpdateDt() == null;
    }
}

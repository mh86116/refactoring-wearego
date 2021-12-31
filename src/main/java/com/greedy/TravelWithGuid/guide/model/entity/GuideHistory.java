package com.greedy.TravelWithGuid.guide.model.entity;

import com.greedy.TravelWithGuid.cmmn.model.entity.BaseEntity;
import com.greedy.TravelWithGuid.guide.model.enums.GuideCategory;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GUIDE_HISTORY")
public class GuideHistory extends BaseEntity implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_NO")
    private Long id;

    @JoinColumn(name = "GUIDE_NO", referencedColumnName = "GUIDE_NO")
    @Column(name = "REF_NO")
    private Long refNo;

    @Column(name = "CATEGORY")
    private GuideCategory category;

    @Column(name = "BEFORE_VALUE")
    private String beforeValue;

    @Column(name = "AFTER_VALUE")
    private String afterValue;


    public static GuideHistory createHistory(Guide guide, Object history, GuideCategory types) {
        return GuideHistory.builder()
                .refNo(guide.getId())
                .category(types)
                .beforeValue(String.valueOf(history))
                .build();
    }

    public static GuideHistory updateGuide(Long id, String history, GuideCategory types) {
        return GuideHistory.builder()
                .refNo(id)
                .category(types)
                .beforeValue(String.valueOf(history))
                .build();
    }

    @Override
    public boolean isNew() {
        return getUpdateDt() == null;
    }

}

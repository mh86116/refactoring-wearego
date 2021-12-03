package com.greedy.TravelWithGuid.guide.model.entity;

import com.greedy.TravelWithGuid.cmmn.model.entity.BaseModifyEntity;
import com.greedy.TravelWithGuid.cmmn.model.entity.BaseTimeModifyEntity;
import com.greedy.TravelWithGuid.guide.model.enums.GuideCategory;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GUIDE_HISTORY")
public class GuideHistory extends BaseModifyEntity implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_NO")
    private Long id;

    @Column(name = "REF_NO")
    private Long refNo;

    @Column(name = "CATEGORY")
    private GuideCategory category;

    @Column(name = "BEFORE_VALUE")
    private String beforeValue;

    @Column(name = "AFTER_VALUE")
    private String afterValue;

    public static GuideHistory createHistory(String history1, GuideCategory types, Long id) {
        return GuideHistory.builder()
                .refNo(id)
                .category(types)
                .beforeValue(history1)
                .build();
    }

    @Override
    public boolean isNew() {
        return getUpdateDt() == null;
    }


}

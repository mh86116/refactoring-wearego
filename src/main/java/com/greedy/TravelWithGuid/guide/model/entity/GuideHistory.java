package com.greedy.TravelWithGuid.guide.model.entity;

import com.greedy.TravelWithGuid.cmmn.model.entity.BaseTimeModifyEntity;
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
public class GuideHistory extends BaseTimeModifyEntity implements Persistable<Long> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_NO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GUIDE_NO")
    private Guide guide;

    @Column(name = "CATEGORY")
    private GuideCategory category;

    @Column(name = "BEFORE_VALUE")
    private String beforeValue;

    @Column(name = "AFTER_VALUE")
    private String afterValue;

    @Override
    public boolean isNew() {
        return getCreatedDt() == null;
    }


}

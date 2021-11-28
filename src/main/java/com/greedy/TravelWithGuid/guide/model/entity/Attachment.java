package com.greedy.TravelWithGuid.guide.model.entity;

import com.greedy.TravelWithGuid.cmmn.model.entity.BaseTimeRegisterEntity;
import com.greedy.TravelWithGuid.guide.model.enums.PhotoCategory;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Getter @Entity
@Builder @ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ATTACHMENT")
public class Attachment extends BaseTimeRegisterEntity implements Persistable<Long> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTACHMENT_NO")
    private Long id;

    @Enumerated(STRING)
    @Column(name = "CATEGORY_")
    private PhotoCategory category;

    @Column(name = "ORIGINAL_NAME")
    private String originalName;

    @Column(name = "SAVED_NAME")
    private String savedName;

    @Column(name = "SAVE_PATH")
    private String savePath;

    @Column(name = "IS_ENABLE")
    private boolean isEnable;

    @Override
    public boolean isNew() {
        return getCreatedDt() == null;
    }
}

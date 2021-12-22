package com.greedy.TravelWithGuid.guide.model.entity;

import com.greedy.TravelWithGuid.cmmn.model.entity.BaseTimeEntity;
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
public class Attachment extends BaseTimeEntity implements Persistable<Long> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTACHMENT_NO")
    private Long id;

    @Enumerated(STRING)
    @Column(name = "CATEGORY")
    private PhotoCategory category;

    @JoinColumns({@JoinColumn(name = "GUIDE_NO", referencedColumnName = "GUIDE_NO"), @JoinColumn(name = "GOODS_NO", referencedColumnName = "GOODS_NO")})
    @Column(name = "REF_NO")
    private Long refNo;

    @Column(name = "ORIGINAL_NAME")
    private String originalName;

    @Column(name = "SAVED_NAME")
    private String savedName;

    @Column(name = "SAVE_PATH")
    private String savePath;

    @Column(name = "IS_ENABLE")
    private boolean isEnable;

    public static Attachment create(Long id, String originalFilename, String savedName, String path, String name) {
        return Attachment.builder()
                .refNo(id)
                .category(PhotoCategory.valueOf(name))
                .originalName(originalFilename)
                .savedName(savedName)
                .savePath(path)
                .isEnable(false)
                .build();
    }

    public void patchPhoto(Long id, boolean b) {
        this.id = id;
        this.isEnable = b;
    }

    @Override
    public boolean isNew() {
        return getCreatedDt() == null;
    }

}

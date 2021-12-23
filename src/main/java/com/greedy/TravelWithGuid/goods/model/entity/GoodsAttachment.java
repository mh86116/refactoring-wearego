package com.greedy.TravelWithGuid.goods.model.entity;

import com.greedy.TravelWithGuid.cmmn.model.entity.BaseTimeEntity;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Getter @Entity
@Builder @ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GOODS_ATTACHMENT")
public class GoodsAttachment extends BaseTimeEntity implements Persistable<Long> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTACHMENT_NO")
    private Long id;

    @JoinColumn(name = "GOODS_NO", referencedColumnName = "GOODS_NO")
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

    public static GoodsAttachment create(Long id, String originalFilename, String savedName, String path) {
        return GoodsAttachment.builder()
                .refNo(id)
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

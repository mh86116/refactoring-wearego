package com.greedy.TravelWithGuid.guide.model.entity;

import com.greedy.TravelWithGuid.cmmn.model.entity.BaseTimeEntity;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GOODS_OPTION")
public class GoodsOption extends BaseTimeEntity implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_CODE")
    private Long id;

    @Column(name = "OPTION_NAME")
    private String optionName;

    @Column(name = "OPTION_PRICE")
    private String optionPrice;

    @JoinColumn(name = "GOODS_NO", referencedColumnName = "GOODS_NO")
    @Column(name = "REF_NO")
    private Long refNo;

    @Column(name = "IS_ENABLE")
    private boolean isEnable;

    public static GoodsOption create(Long id, String name, String price) {
        return GoodsOption.builder()
                .refNo(id)
                .optionName(name)
                .optionPrice(price)
                .isEnable(false)
                .build();
    }

    @Override
    public boolean isNew() {
        return getCreatedDt() == null;
    }
}

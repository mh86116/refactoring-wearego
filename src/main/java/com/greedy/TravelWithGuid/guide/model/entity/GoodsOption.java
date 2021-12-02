package com.greedy.TravelWithGuid.guide.model.entity;

import com.greedy.TravelWithGuid.cmmn.model.entity.BaseTimeRegisterEntity;
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
public class GoodsOption extends BaseTimeRegisterEntity implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_CODE")
    private Long id;

    @Column(name = "OPTION_NAME")
    private String optionName;

    @Column(name = "OPTION_PRICE")
    private String optionPrice;

    @ManyToOne
    @JoinColumn(name = "GOODS_NO")
    private Goods goods;

    @Column(name = "IS_ENABLE")
    private boolean isEnable;

    public static GoodsOption create(Goods goods, String name, String price) {
        return GoodsOption.builder()
                .goods(goods)
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

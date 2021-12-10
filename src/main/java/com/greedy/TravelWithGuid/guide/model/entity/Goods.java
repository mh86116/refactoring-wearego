package com.greedy.TravelWithGuid.guide.model.entity;

import com.greedy.TravelWithGuid.cmmn.model.entity.BaseTimeEntity;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GOODS")
public class Goods extends BaseTimeEntity implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GOODS_NO")       //상품 번호
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GUIDE_NO")         //가이드번호
    private Guide guide;

    @Column(name = "TITLE")         //상품제목
    private String title;

    @Column(name = "PRICE")         //상품가격
    private Integer price;

    @Column(name = "PLACE")        //미팅장소
    private String place;

    @Column(name = "START_DT")
    private LocalDate startDt;     //판매시작일자

    @Column(name = "END_DT")
    private LocalDate endDt;      //핀메종료일자

    @Column(name = "PERSON")          //인원수
    private Integer person;

    @Column(name = "BODY", length = 100000000)   //자세한 안내사항
    private String body;

    @Column(name = "IS_ENABLE")         //활성화
    private boolean isEnable;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "refNo")
    private List<Attachment> attachments;

    public static Goods createGoods(Guide guide, String title, String price, String place, String startDt, String endDt, String person, String body, boolean b) {
        return Goods.builder()
                .guide(guide)
                .title(title)
                .price(Integer.valueOf(price))
                .place(place)
                .startDt(LocalDate.parse(startDt))
                .endDt(LocalDate.parse(endDt))
                .person(Integer.valueOf(person))
                .body(body)
                .isEnable(false)
                .build();
    }

    @Override
    public boolean isNew() { return getCreatedDt() == null; }
}
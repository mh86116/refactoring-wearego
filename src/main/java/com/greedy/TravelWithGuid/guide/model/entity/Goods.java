package com.greedy.TravelWithGuid.guide.model.entity;

import com.greedy.TravelWithGuid.cmmn.model.entity.BaseTimeRegisterEntity;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GOODS")
public class Goods extends BaseTimeRegisterEntity implements Persistable<Long> {
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

    @Override
    public boolean isNew() { return getCreatedDt() == null; }
}
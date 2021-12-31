package com.greedy.TravelWithGuid.cmmn.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter @Setter
//BaseEntity 를 상속한 entity 들은 BaseEntity 에 있는 멤버변수들을 모두 컬럼으로 인식되도록 함
@MappedSuperclass
//Auditing 기능을 사용하겠다! 자동으로 값을 매핑시키겠다는 의미
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity extends BaseTimeEntity {
    @CreatedDate
    @CreatedBy
    @Column(name = "CREATE_BY", updatable = false)
    private String createBy;

    @LastModifiedBy
    private String updatedBy;


}

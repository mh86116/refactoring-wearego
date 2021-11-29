package com.greedy.TravelWithGuid.cmmn.model.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModifyEntity {

    @LastModifiedDate
    @Column(name = "updated_dt")
    private LocalDateTime updateDt;

    @LastModifiedBy
    private String updateBy;

    @PrePersist
    private void updateDate() {
        /* 로컬 컴퓨터의 현재 날짜와 시간 정보 */
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (this.updateDt == null) {
            this.updateDt = currentDateTime;
        }
    }
}
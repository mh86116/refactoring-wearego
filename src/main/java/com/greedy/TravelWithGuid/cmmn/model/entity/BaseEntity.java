package com.greedy.TravelWithGuid.cmmn.model.entity;

import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity extends BaseTimeEntity {
    @LastModifiedDate
    @Column(name = "UPDATE_DT")
    private LocalDateTime updateDate;

    @PrePersist
    private void updateDate(){
        if (this.updateDate == null) {
            this.updateDate=this.getCreatedDt();
        }
    }
}

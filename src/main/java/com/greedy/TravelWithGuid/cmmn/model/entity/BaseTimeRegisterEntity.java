package com.greedy.TravelWithGuid.cmmn.model.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.security.Principal;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeRegisterEntity {

    @CreatedDate
    @Column(name = "created_dt", updatable = false)
    private LocalDateTime createdDt;

    @CreatedDate
    @CreatedBy
    @Column(name = "CREATE_BY", updatable = false)
    private String createBy;

}
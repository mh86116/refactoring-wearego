package com.greedy.TravelWithGuid.member.model.entity;


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
@Table(name = "MEMBER")
public class Member extends BaseTimeEntity implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_NO", nullable = false)
    private Long id;

    @Column(name = "AUTHORITY")
    private String authority;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PWD")
    private String pwd;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "IS_ENABLE")
    private boolean isEnable;

    @Override
    public boolean isNew() {
        return getCreatedDt() == null;
    }
}
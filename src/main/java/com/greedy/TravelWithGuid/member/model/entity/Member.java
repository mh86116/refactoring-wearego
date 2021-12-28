package com.greedy.TravelWithGuid.member.model.entity;

import com.greedy.TravelWithGuid.cmmn.model.entity.BaseTimeEntity;
import com.greedy.TravelWithGuid.member.model.enums.Role;
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

    @Column(name = "PWD", length = 100)
    private String pwd;

    @Column(name = "NICKNAME", unique = true)
    private String nickname;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "IS_ENABLE")
    private boolean isEnable;

    @Override
    public boolean isNew() {
        return getCreatedDt() == null;
    }

    public void patchMemberGuide(Long id) {
        this.id = id;
        this.authority = Role.GUIDE.getValue();
    }

    public void patchUpdateMember(Long id, String nickname, String phone) {
        this.id = id;
        this.nickname = (nickname != null) ? nickname : this.nickname;
        this.phone = (phone != null) ? phone : this.phone;
    }

    public void patchPwdUpdate(Long id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    public void delete(Long id, boolean isEnable) {
        this.id = id;
        this.isEnable = isEnable;
    }
}
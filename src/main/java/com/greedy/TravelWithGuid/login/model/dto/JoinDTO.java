package com.greedy.TravelWithGuid.login.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@AllArgsConstructor
public class JoinDTO {
    private String email;
    private String pwd;
    private String nickname;
    private String phone;
    private List<GrantedAuthority> authorities;
}

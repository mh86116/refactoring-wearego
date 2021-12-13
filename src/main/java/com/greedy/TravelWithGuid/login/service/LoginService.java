package com.greedy.TravelWithGuid.login.service;

import com.greedy.TravelWithGuid.login.model.dto.JoinDTO;
import com.greedy.TravelWithGuid.member.model.entity.Member;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface LoginService extends UserDetailsService {
    boolean join(JoinDTO dto);

    boolean checkEmail(String email);

    boolean checkNickname(String nickname);
}

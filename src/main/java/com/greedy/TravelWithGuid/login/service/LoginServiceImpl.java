package com.greedy.TravelWithGuid.login.service;

import com.greedy.TravelWithGuid.login.model.dto.JoinDTO;
import com.greedy.TravelWithGuid.login.model.dto.LoginDTO;
import com.greedy.TravelWithGuid.member.model.enums.Role;
import com.greedy.TravelWithGuid.member.model.entity.Member;
import com.greedy.TravelWithGuid.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean join(JoinDTO dto) {
        try {
            Member member = Member.builder()
                    .email(dto.getEmail())
                    .pwd(passwordEncoder.encode(dto.getPwd()))
                    .nickname(dto.getNickname().replace(" ", ""))
                    .phone(dto.getPhone().replace("-", ""))
                    .authority(Role.MEMBER.getValue())
                    .isEnable(true)
                    .build();
            memberRepository.save(member);
            return true;
        } catch (Exception e) {
            log.error("join error!! " + e);
            return false;
        }
    }

    @Override
    public boolean checkEmail(String email) { return memberEmail(email) == null; }

    @Override
    public boolean checkNickname(String nickname) { return memberNickname(nickname) == null; }

    /******************************************************
     * 공통 로직
     ******************************************************/
    private Member memberEmail(String email) {
        return memberRepository.findByEmail(email);
    }
    private Member memberNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberEmail(username);
        if (member == null) {
            throw new UsernameNotFoundException("USER NOT FOUND! / userId : " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(member.getAuthority()));
        return new LoginDTO(member.getEmail(), member.getPwd(), authorities, member.isEnable());
    }
}
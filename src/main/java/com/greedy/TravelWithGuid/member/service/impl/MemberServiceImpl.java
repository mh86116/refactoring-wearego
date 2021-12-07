package com.greedy.TravelWithGuid.member.service.impl;

import com.greedy.TravelWithGuid.guide.model.dto.UpdateGuideDTO;
import com.greedy.TravelWithGuid.member.model.dto.MemberDTO;
import com.greedy.TravelWithGuid.member.model.entity.Member;
import com.greedy.TravelWithGuid.member.model.entity.MemberHistory;
import com.greedy.TravelWithGuid.member.model.enums.MemberCategory;
import com.greedy.TravelWithGuid.member.repository.MemberHistoryRepository;
import com.greedy.TravelWithGuid.member.repository.MemberRepository;
import com.greedy.TravelWithGuid.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberHistoryRepository historyRepository;
    private final PasswordEncoder encoder;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Page<MemberDTO> getMembers(String word, Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 20);
        return memberRepository.getMembers(word, pageable);
    }

    @Override
    public Member updateMember(String name) {
        return memberRepository.findByEmail(name);
    }

    @Override
    public void patchUpdate(Long id, UpdateGuideDTO dto) {
        Member member = findMemberById(id);
        List<String> before = List.of(member.getNickname(), member.getPhone());
        member.patchUpdateMember(id, dto.getNickname(), dto.getPhone().replace("-" , ""));
        memberRepository.save(member);
        //변경이력
        List<String> value = List.of(member.getNickname(), member.getPhone().replace("-", ""));
        List<MemberCategory> type = List.of(MemberCategory.NICKNAME, MemberCategory.PHONE);
        for (int i = 0; i < value.size(); i++) {
            MemberHistory history = MemberHistory.updateMember(member, value.get(i), type.get(i), before.get(i));
            historyRepository.save(history);
        }
    }

    @Override
    public void patchPwdUpdate(Long id, String pwd) {
        Member member = findMemberById(id);
        String pwdUpdate = encoder.encode(pwd);
        if (!passwordEncoder.matches(member.getPwd(), pwdUpdate)) {
        member.patchPwdUpdate(id, pwdUpdate);
        memberRepository.save(member);
        }
    }

    /********************************************
     * 공통 로직
     ********************************************/
    private Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다. memberId : " + id));
    }
}

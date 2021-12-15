package com.greedy.TravelWithGuid.member.service.impl;

import com.greedy.TravelWithGuid.guide.model.dto.UpdateGuideDTO;
import com.greedy.TravelWithGuid.member.model.dto.MemberCheckDTO;
import com.greedy.TravelWithGuid.member.model.dto.MemberDTO;
import com.greedy.TravelWithGuid.member.model.dto.MemberDeleteDTO;
import com.greedy.TravelWithGuid.member.model.dto.UpdateMemberDTO;
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

    @Override
    public Page<MemberDTO> getMembers(String word, Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 20);
        return memberRepository.getMembers(word, pageable);
    }

    @Override
    public boolean checkResult(MemberCheckDTO dto) {
        Member member = findByEmail(dto.getEmail());
        return encoder.matches(dto.getPwd(), member.getPwd());
    }

    @Override
    public Member updateMember(String name) {
        return memberRepository.findByEmail(name);
    }

    @Override
    public void patchUpdate(Long id, UpdateMemberDTO dto) {
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
        String pwdUpdate = encoder.encode(pwd);
        Member member = findMemberById(id);
        member.patchPwdUpdate(id, pwdUpdate);
        memberRepository.save(member);
    }

    @Override
    public boolean delete(MemberDeleteDTO dto) {
        Member member = findByEmail(dto.getEmail());
        if (encoder.matches(dto.getPwd(), member.getPwd())) {
            member.delete(member.getId(), false);
            memberRepository.save(member);
            MemberHistory history = MemberHistory.delete(member, "탈퇴", dto.getReason());
            historyRepository.save(history);
            return true;
        }
        return false;
    }

    /********************************************
     * 공통 로직
     ********************************************/
    private Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다. memberId : " + id));
    }

    private Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}

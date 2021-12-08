package com.greedy.TravelWithGuid.member.service;

import com.greedy.TravelWithGuid.guide.model.dto.UpdateGuideDTO;
import com.greedy.TravelWithGuid.member.model.dto.MemberCheckDTO;
import com.greedy.TravelWithGuid.member.model.dto.MemberDTO;
import com.greedy.TravelWithGuid.member.model.dto.MemberDeleteDTO;
import com.greedy.TravelWithGuid.member.model.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {

    Page<MemberDTO> getMembers(String word, Pageable pageable);

    boolean checkResult(MemberCheckDTO dto);

    Member updateMember(String name);

    void patchUpdate(Long id, UpdateGuideDTO dto);

    void patchPwdUpdate(Long id, String pwd);

    boolean delete(MemberDeleteDTO dto);
}

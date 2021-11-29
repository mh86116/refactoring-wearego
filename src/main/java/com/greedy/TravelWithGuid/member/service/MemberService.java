package com.greedy.TravelWithGuid.member.service;

import com.greedy.TravelWithGuid.member.model.dto.MemberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {

    Page<MemberDTO> getMembers(String word, Pageable pageable);

}

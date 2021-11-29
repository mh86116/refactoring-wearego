package com.greedy.TravelWithGuid.member.repository;

import com.greedy.TravelWithGuid.member.model.dto.MemberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberDsl {
    Page<MemberDTO> getMembers(String word, Pageable pageable);
}

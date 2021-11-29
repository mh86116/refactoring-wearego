package com.greedy.TravelWithGuid.member.repository;

import com.greedy.TravelWithGuid.member.model.dto.MemberDTO;
import com.greedy.TravelWithGuid.member.model.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberDsl {

    Member findByEmail(String email);

}

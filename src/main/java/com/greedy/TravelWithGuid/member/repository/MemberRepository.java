package com.greedy.TravelWithGuid.member.repository;

import com.greedy.TravelWithGuid.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}

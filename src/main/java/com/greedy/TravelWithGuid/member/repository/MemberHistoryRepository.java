package com.greedy.TravelWithGuid.member.repository;

import com.greedy.TravelWithGuid.member.model.entity.MemberHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHistoryRepository extends JpaRepository<MemberHistory, Long> {
}

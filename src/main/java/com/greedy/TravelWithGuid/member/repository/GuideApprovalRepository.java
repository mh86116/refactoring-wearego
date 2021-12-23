package com.greedy.TravelWithGuid.member.repository;

import com.greedy.TravelWithGuid.guide.model.entity.Examine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideApprovalRepository extends JpaRepository<Examine, Long> {
}

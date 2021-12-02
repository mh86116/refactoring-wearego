package com.greedy.TravelWithGuid.guide.repository;

import com.greedy.TravelWithGuid.guide.model.entity.GuideHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GuideHistoryRepository extends JpaRepository<GuideHistory, Long> {
    @Query("select h from GuideHistory h where h.refNo = :id")
    GuideHistory findByRefNo(@Param("id") Long id);
}

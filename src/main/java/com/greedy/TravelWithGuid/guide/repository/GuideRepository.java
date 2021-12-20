package com.greedy.TravelWithGuid.guide.repository;

import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GuideRepository extends JpaRepository<Guide, Long>, GuideDsl {
    @Query("select g from Guide g where g.member.id = :id")
    Guide findByMember(@Param("id") Long id);
}

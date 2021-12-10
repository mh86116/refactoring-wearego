package com.greedy.TravelWithGuid.guide.repository;

import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GuideRepository extends JpaRepository<Guide, Long>, GuideDsl {
    Guide findByEmail(String email);

    @Query("select g from Guide g where g.member.email = :name")
    Guide findRefNo(@Param("name") String name);
}

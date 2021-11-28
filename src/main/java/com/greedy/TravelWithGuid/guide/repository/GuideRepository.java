package com.greedy.TravelWithGuid.guide.repository;

import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideRepository extends JpaRepository<Guide, Long> {
    Guide findByEmail(String email);
}

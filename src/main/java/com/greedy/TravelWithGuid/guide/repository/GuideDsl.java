package com.greedy.TravelWithGuid.guide.repository;

import com.greedy.TravelWithGuid.guide.model.dto.GuideDTO;
import com.greedy.TravelWithGuid.member.model.dto.RejectGuideDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GuideDsl {
    Page<GuideDTO> getGuides(String word, Pageable pageable);

    Page<RejectGuideDTO> getApproval(String word, Pageable pageable, String type);
}

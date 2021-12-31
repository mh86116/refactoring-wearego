package com.greedy.TravelWithGuid.guide.repository;

import com.greedy.TravelWithGuid.guide.model.dto.GuideDTO;
import com.greedy.TravelWithGuid.guide.model.entity.Examine;
import com.greedy.TravelWithGuid.member.model.dto.RejectGuideDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GuideDsl {
    Page<GuideDTO> getGuideApproval(String word, Pageable pageable, String type);

    Page<RejectGuideDTO> getApproval(String word, Pageable pageable, String type);

}

package com.greedy.TravelWithGuid.guide.repository;

import com.greedy.TravelWithGuid.guide.model.dto.GuideDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GuideDsl {
    Page<GuideDTO> getGuides(String word, Pageable pageable, boolean name);
}

package com.greedy.TravelWithGuid.guide.service;

import com.greedy.TravelWithGuid.guide.model.dto.GuideDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GuideService {
    Page<GuideDTO> getGuides(String word, Pageable pageable);
}

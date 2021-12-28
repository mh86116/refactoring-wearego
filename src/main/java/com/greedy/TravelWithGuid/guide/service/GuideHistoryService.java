package com.greedy.TravelWithGuid.guide.service;

import com.greedy.TravelWithGuid.guide.model.entity.Guide;

public interface GuideHistoryService {

    void history(Guide guide);

    void updateGuide(Long id, String email, String bank, String account);
}

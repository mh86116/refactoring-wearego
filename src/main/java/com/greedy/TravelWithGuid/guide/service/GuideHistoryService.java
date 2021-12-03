package com.greedy.TravelWithGuid.guide.service;

import com.greedy.TravelWithGuid.guide.model.enums.GuideRank;
import com.greedy.TravelWithGuid.guide.model.enums.Warning;

public interface GuideHistoryService {

    void getGuideSignUpHistory(String email, String bank, String account, Long id);
}

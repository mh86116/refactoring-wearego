package com.greedy.TravelWithGuid.guide.service.impl;

import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import com.greedy.TravelWithGuid.guide.model.entity.GuideHistory;
import com.greedy.TravelWithGuid.guide.model.enums.GuideCategory;
import com.greedy.TravelWithGuid.guide.model.enums.GuideRank;
import com.greedy.TravelWithGuid.guide.model.enums.Warning;
import com.greedy.TravelWithGuid.guide.repository.GuideHistoryRepository;
import com.greedy.TravelWithGuid.guide.service.GuideHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuideHistoryServiceImpl implements GuideHistoryService {
    private final GuideHistoryRepository historyRepository;

    @Override
    public void getGuideSignUpHistory(String email, String bank, String account, GuideRank rank, Warning warning, boolean b, Long id) {
        try {
            List<String> list = List.of(email, bank, account, String.valueOf(rank), String.valueOf(warning), String.valueOf(false));
            List<GuideCategory> categories = new ArrayList<>(Arrays.asList(GuideCategory.values()));
            for (int i = 0; i < list.size(); i++) {
                GuideHistory history = GuideHistory.createHistory(list.get(i), categories.get(i), id);
                historyRepository.save(history);
            }
        } catch (IndexOutOfBoundsException e) {
            log.error("history save error!! " + e);
        }
    }
}
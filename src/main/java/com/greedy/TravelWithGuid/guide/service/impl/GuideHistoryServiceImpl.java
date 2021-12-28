package com.greedy.TravelWithGuid.guide.service.impl;

import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import com.greedy.TravelWithGuid.guide.model.entity.GuideHistory;
import com.greedy.TravelWithGuid.guide.model.enums.GuideCategory;
import com.greedy.TravelWithGuid.guide.repository.GuideHistoryRepository;
import com.greedy.TravelWithGuid.guide.service.GuideHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuideHistoryServiceImpl implements GuideHistoryService {
    private final GuideHistoryRepository historyRepository;
    List<GuideCategory> type = List.of(GuideCategory.NAME, GuideCategory.EMAIL, GuideCategory.BANK,
            GuideCategory.ACCOUNT, GuideCategory.RANK, GuideCategory.WARNING);
    List<GuideCategory> types = List.of(GuideCategory.EMAIL, GuideCategory.BANK,
            GuideCategory.ACCOUNT, GuideCategory.RANK, GuideCategory.WARNING);

    @Override
    public void history(Guide guide) {
        List<Object> before = List.of(guide.getName(), guide.getEmail(), guide.getBank(),
                guide.getAccount(), guide.getRank(), guide.getWarning());
        for (int i = 0; i < before.size(); i++) {
            GuideHistory history = GuideHistory.createHistory(guide, before.get(i), type.get(i));
            historyRepository.save(history);
        }
    }

    @Override
    public void updateGuide(Long id, String email, String bank, String account) {
        List<String> before = List.of(email, bank, account);
        for (int i = 0; i < before.size(); i++) {
            GuideHistory history = GuideHistory.updateGuide(id, before.get(i), types.get(i));
            historyRepository.save(history);
        }

    }
}
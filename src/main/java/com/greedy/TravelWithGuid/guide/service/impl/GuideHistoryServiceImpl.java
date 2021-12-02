package com.greedy.TravelWithGuid.guide.service.impl;

import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import com.greedy.TravelWithGuid.guide.model.entity.GuideHistory;
import com.greedy.TravelWithGuid.guide.model.enums.GuideCategory;
import com.greedy.TravelWithGuid.guide.repository.GuideHistoryRepository;
import com.greedy.TravelWithGuid.guide.service.GuideHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuideHistoryServiceImpl implements GuideHistoryService {
    private final GuideHistoryRepository historyRepository;

    @Override
    public void createGuideHistory(Guide guide) {
        GuideHistory history;
        if (guide.getEmail() != null) {
            history = GuideHistory.builder()
                    .refNo(guide.getId())
                    .category(GuideCategory.EMAIL)
                    .afterValue(guide.getEmail())
                    .build();
            historyRepository.save(history);
        }
        if (guide.getBank() != null) {
            history = GuideHistory.builder()
                    .refNo(guide.getId())
                    .category(GuideCategory.BANK)
                    .afterValue(guide.getBank())
                    .build();
            historyRepository.save(history);
        }
        if (guide.getAccount() != null) {
            history = GuideHistory.builder()
                    .refNo(guide.getId())
                    .category(GuideCategory.ACCOUNT)
                    .afterValue(guide.getAccount())
                    .build();
            historyRepository.save(history);
        }
        if (guide.getWarning() != null) {
            history = GuideHistory.builder()
                    .refNo(guide.getId())
                    .category(GuideCategory.WARNING)
                    .afterValue(guide.getWarning().getValue())
                    .build();
            historyRepository.save(history);
        }
        if (guide.getRank() != null) {
            history = GuideHistory.builder()
                    .refNo(guide.getId())
                    .category(GuideCategory.RANK)
                    .afterValue(guide.getRank().getValue())
                    .build();
            historyRepository.save(history);
        }
        if (!guide.isApprovalYn()) {
            history = GuideHistory.builder()
                    .refNo(guide.getId())
                    .category(GuideCategory.APPROVAL)
                    .afterValue(String.valueOf(guide.isApprovalYn()))
                    .build();
            historyRepository.save(history);
        }
    }

}
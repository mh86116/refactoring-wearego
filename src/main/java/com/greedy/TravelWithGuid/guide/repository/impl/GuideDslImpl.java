package com.greedy.TravelWithGuid.guide.repository.impl;

import com.greedy.TravelWithGuid.guide.model.dto.GuideDTO;
import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import com.greedy.TravelWithGuid.guide.repository.GuideDsl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.greedy.TravelWithGuid.guide.model.entity.QGuide.guide;

@Repository
@RequiredArgsConstructor
public class GuideDslImpl implements GuideDsl {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<GuideDTO> getGuides(String word, Pageable pageable, boolean name) {
        if (name) {
            List<Guide> guides = selectApprovalGuide(word, pageable, true);
            List<GuideDTO> content = toGuideListDTOS(guides);
            JPAQuery<Guide> countQuery = queryFactory.selectFrom(guide);
            return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
        } else {
            List<Guide> guides = selectApprovalGuide(word, pageable, false);
            List<GuideDTO> content = toGuideListDTOS(guides);
            JPAQuery<Guide> countQuery = queryFactory.selectFrom(guide);
            return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
        }
    }

    private List<Guide> selectApprovalGuide(String word, Pageable pageable, boolean name) {
        return queryFactory
                .selectFrom(guide)
                .where(getGuidePredicate(word)
                        .and(guide.approvalYn.eq(name)))
                .orderBy(guide.createdDt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private List<GuideDTO> toGuideListDTOS(List<Guide> guides) {
        return guides.stream()
                .map(GuideDTO::new)
                .collect(Collectors.toList());
    }

    private BooleanBuilder getGuidePredicate(String word) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(word)) {
            builder.and(
                    (guide.id.stringValue().containsIgnoreCase(word))
                            .or(guide.name.stringValue().containsIgnoreCase(word))
                            .or(guide.email.stringValue().containsIgnoreCase(word))
                            .or(guide.bank.stringValue().containsIgnoreCase(word))
            );
        }
        return builder;
    }
}

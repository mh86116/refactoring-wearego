package com.greedy.TravelWithGuid.guide.repository.impl;

import com.greedy.TravelWithGuid.guide.model.dto.GuideDTO;
import com.greedy.TravelWithGuid.guide.model.dto.QGuideDTO;
import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import com.greedy.TravelWithGuid.guide.repository.GuideDsl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.greedy.TravelWithGuid.guide.model.entity.QGuide.guide;

@Repository
@RequiredArgsConstructor
public class GuideDslImpl implements GuideDsl {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<GuideDTO> getGuides(String word, Pageable pageable) {
        QueryResults<GuideDTO> results = queryFactory
                .select(new QGuideDTO(guide))
                .from(guide)
                .where(getGuidePredicate(word))
                .orderBy(guide.createdDt.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        List<GuideDTO> content = results.getResults();
        JPAQuery<Guide> countQuery = queryFactory.selectFrom(guide);
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
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

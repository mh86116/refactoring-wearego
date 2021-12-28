package com.greedy.TravelWithGuid.guide.repository.impl;

import com.greedy.TravelWithGuid.guide.model.dto.GuideDTO;
import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import com.greedy.TravelWithGuid.guide.model.enums.Approval;
import com.greedy.TravelWithGuid.guide.model.enums.GuideCategory;
import com.greedy.TravelWithGuid.guide.repository.GuideDsl;
import com.greedy.TravelWithGuid.member.model.dto.RejectGuideDTO;
import com.greedy.TravelWithGuid.guide.model.entity.Examine;
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
import static com.greedy.TravelWithGuid.guide.model.entity.QExamine.examine;

@Repository
@RequiredArgsConstructor
public class GuideDslImpl implements GuideDsl {
    private final JPAQueryFactory queryFactory;

    //가이드 신청 관리
    @Override
    public Page<GuideDTO> getGuides(String word, Pageable pageable) {
        List<Guide> guides = selectGuide(word, pageable);
        List<GuideDTO> content = toGuideListDTOS(guides);
        JPAQuery<Guide> countQuery = queryFactory.selectFrom(guide);
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<RejectGuideDTO> getApproval(String word, Pageable pageable, String type) {
        List<Examine> Examines = selectApproval(word, pageable, type);
        List<RejectGuideDTO> content = toApproval(Examines);
        JPAQuery<Examine> countQuery = queryFactory.selectFrom(examine);
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    //가이드 신청
    private List<Guide> selectGuide(String word, Pageable pageable) {
        return queryFactory
                .selectFrom(guide)
                .join(examine).on(guide.id.eq(guide.id))
                .where(examine.approval.eq(Approval.SUBMIT)
                        .and(getGuidePredicate(word)))
                .orderBy(examine.createdDt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }


    private List<Examine> selectApproval(String word, Pageable pageable, String type) {
        return queryFactory
                .selectFrom(examine)
                .where(getGuidePredicate(word)
                        .and(examine.approval.eq(Approval.valueOf(type))))
                .orderBy(examine.createdDt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private List<RejectGuideDTO> toApproval(List<Examine> Examines) {
        return Examines.stream()
                .map(RejectGuideDTO::new)
                .collect(Collectors.toList());
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

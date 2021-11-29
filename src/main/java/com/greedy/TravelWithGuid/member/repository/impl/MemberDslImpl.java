package com.greedy.TravelWithGuid.member.repository.impl;

import com.greedy.TravelWithGuid.member.model.dto.MemberDTO;
import com.greedy.TravelWithGuid.member.model.dto.QMemberDTO;
import com.greedy.TravelWithGuid.member.model.entity.Member;
import com.greedy.TravelWithGuid.member.repository.MemberDsl;
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

import static com.greedy.TravelWithGuid.member.model.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberDslImpl implements MemberDsl {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MemberDTO> getMembers(String word, Pageable pageable) {
        QueryResults<MemberDTO> results = queryFactory
                .select(new QMemberDTO(member))
                .from(member)
                .where(getMemberPredicate(word))
                .orderBy(member.createdDt.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        List<MemberDTO> content = results.getResults();
        JPAQuery<Member> countQuery = queryFactory.selectFrom(member);
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private BooleanBuilder getMemberPredicate(String word) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(word)) {
            builder.and(
                    (member.id.stringValue().containsIgnoreCase(word))
                            .or(member.nickname.stringValue().containsIgnoreCase(word))
                            .or(member.phone.stringValue().containsIgnoreCase(word))
            );
        }
        return builder;
    }
}

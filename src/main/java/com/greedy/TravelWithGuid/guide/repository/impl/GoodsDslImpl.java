package com.greedy.TravelWithGuid.guide.repository.impl;

import com.greedy.TravelWithGuid.guide.model.dto.GoodsDTO;
import com.greedy.TravelWithGuid.guide.model.dto.QGoodsDTO;
import com.greedy.TravelWithGuid.guide.model.entity.Goods;
import com.greedy.TravelWithGuid.guide.model.enums.PhotoCategory;
import com.greedy.TravelWithGuid.guide.repository.GoodsDsl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Min;
import java.util.List;

import static com.greedy.TravelWithGuid.guide.model.entity.QAttachment.attachment;
import static com.greedy.TravelWithGuid.guide.model.entity.QGoods.goods;

@Repository
@RequiredArgsConstructor
public class GoodsDslImpl implements GoodsDsl {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<GoodsDTO> getGoodsList(String word, Pageable pageable) {
        QueryResults<GoodsDTO> results = queryFactory
                .select(new QGoodsDTO(goods))
                .from(goods)
                .join(goods.attachments, attachment).fetchJoin()
                .where(
                        attachment.category.eq(PhotoCategory.valueOf("GOODS"))
                                .and(goods.id.in(
                                        JPAExpressions
                                                .select(attachment.id.min())
                                                .from(attachment)
                                ))
                        .and(
                                getGoodsPredicate(word))
        )
                .orderBy(goods.createdDt.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        List<GoodsDTO> content = results.getResults();
        JPAQuery<Goods> countQuery = queryFactory.selectFrom(goods);
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private BooleanBuilder getGoodsPredicate(String word) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(word)) {
            builder.and(
                    (goods.id.stringValue().containsIgnoreCase(word))
//                            .or(goods.startDt.stringValue().containsIgnoreCase(word))
//                            .or(goods.options.get(0).optionName.stringValue().containsIgnoreCase(word))
                            .or(goods.place.stringValue().containsIgnoreCase(word))
            );
        }
        return builder;
    }
}

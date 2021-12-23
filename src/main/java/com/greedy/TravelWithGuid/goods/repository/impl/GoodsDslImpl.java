package com.greedy.TravelWithGuid.goods.repository.impl;

import com.greedy.TravelWithGuid.goods.model.dto.GoodsDTO;
import com.greedy.TravelWithGuid.goods.model.dto.QGoodsDTO;
import com.greedy.TravelWithGuid.goods.model.entity.Goods;
import com.greedy.TravelWithGuid.goods.repository.GoodsDsl;
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

import static com.greedy.TravelWithGuid.goods.model.entity.QGoods.goods;

@Repository
@RequiredArgsConstructor
public class GoodsDslImpl implements GoodsDsl {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<GoodsDTO> getGoodsList(String word, Pageable pageable) {
        QueryResults<GoodsDTO> results = queryFactory
                .select(new QGoodsDTO(goods))
                .from(goods)
                .where(
                        getGoodsPredicate(word))
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
                            .or(goods.startDt.stringValue().containsIgnoreCase(word))
                            .or(goods.options.get(0).optionName.stringValue().containsIgnoreCase(word))
                            .or(goods.place.stringValue().containsIgnoreCase(word))
            );
        }
        return builder;
    }
}

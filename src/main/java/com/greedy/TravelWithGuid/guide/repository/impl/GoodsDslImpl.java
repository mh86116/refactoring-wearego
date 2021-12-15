package com.greedy.TravelWithGuid.guide.repository.impl;

import com.greedy.TravelWithGuid.guide.model.dto.GoodsDTO;
import com.greedy.TravelWithGuid.guide.repository.GoodsDsl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GoodsDslImpl implements GoodsDsl {
    @Override
    public Page<GoodsDTO> getGoodsList(String word, Pageable pageable) {
        return null;
    }
}

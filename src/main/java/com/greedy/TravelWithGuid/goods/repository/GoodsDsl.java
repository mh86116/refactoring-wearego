package com.greedy.TravelWithGuid.goods.repository;

import com.greedy.TravelWithGuid.goods.model.dto.GoodsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GoodsDsl {
    Page<GoodsDTO> getGoodsList(String word, Pageable pageable);
}

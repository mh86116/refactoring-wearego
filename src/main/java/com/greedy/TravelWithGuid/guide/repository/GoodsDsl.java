package com.greedy.TravelWithGuid.guide.repository;

import com.greedy.TravelWithGuid.guide.model.dto.GoodsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GoodsDsl {
    Page<GoodsDTO> getGoodsList(String word, Pageable pageable);
}

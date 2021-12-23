package com.greedy.TravelWithGuid.goods.repository;

import com.greedy.TravelWithGuid.goods.model.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Long>, GoodsDsl {
}

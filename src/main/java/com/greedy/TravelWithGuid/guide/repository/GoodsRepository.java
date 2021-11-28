package com.greedy.TravelWithGuid.guide.repository;

import com.greedy.TravelWithGuid.guide.model.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
}

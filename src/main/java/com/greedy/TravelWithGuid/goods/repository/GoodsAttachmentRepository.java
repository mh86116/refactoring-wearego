package com.greedy.TravelWithGuid.goods.repository;

import com.greedy.TravelWithGuid.goods.model.entity.GoodsAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoodsAttachmentRepository extends JpaRepository<GoodsAttachment, Long> {
    @Query("select a from GuideAttachment a where a.refNo = :id")
    GoodsAttachment findByRefNo(@Param("id") Long id);
//    Attachment findByRefNo(Long id);

    @Query("select a from GuideAttachment a where a.refNo = :id")
    GoodsAttachment getRefNo(Long id);

    @Query("select a from GuideAttachment a where a.refNo = :id")
    List<GoodsAttachment> RefNo(@Param("id") Long id);

}
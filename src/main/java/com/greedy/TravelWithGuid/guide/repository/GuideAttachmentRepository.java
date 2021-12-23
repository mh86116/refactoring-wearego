package com.greedy.TravelWithGuid.guide.repository;

import com.greedy.TravelWithGuid.guide.model.entity.GuideAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GuideAttachmentRepository extends JpaRepository<GuideAttachment, Long> {
    @Query("select a from GuideAttachment a where a.refNo = :id")
    GuideAttachment findByRefNo(@Param("id") Long id);
//    Attachment findByRefNo(Long id);

    @Query("select a from GuideAttachment a where a.refNo = :id")
    GuideAttachment getRefNo(Long id);

    @Query("select a from GuideAttachment a where a.refNo = :id")
    List<GuideAttachment> RefNo(@Param("id") Long id);

}
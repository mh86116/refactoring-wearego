package com.greedy.TravelWithGuid.guide.repository;

import com.greedy.TravelWithGuid.guide.model.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    @Query("select a from Attachment a where a.refNo = :id")
    Attachment findByRefNo(@Param("id") Long id);
//    Attachment findByRefNo(Long id);

    @Query("select a from Attachment a where a.refNo = :id")
    Attachment getRefNo(Long id);

    @Query("select a from Attachment a where a.refNo = :id")
    List<Attachment> RefNo(@Param("id") Long id);

}
package com.greedy.TravelWithGuid.guide.repository;

import com.greedy.TravelWithGuid.guide.model.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}

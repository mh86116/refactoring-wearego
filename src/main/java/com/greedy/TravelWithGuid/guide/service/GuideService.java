package com.greedy.TravelWithGuid.guide.service;

import com.greedy.TravelWithGuid.guide.model.dto.EditGuideDTO;
import com.greedy.TravelWithGuid.guide.model.dto.GuideDTO;
import com.greedy.TravelWithGuid.member.model.dto.RejectGuideDTO;
import com.greedy.TravelWithGuid.member.model.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GuideService {
    Page<GuideDTO> getGuides(String word, Pageable pageable, boolean name);

    Page<RejectGuideDTO> getApproval(String word, Pageable pageable, String type);

    boolean getGuideSignUp(List<MultipartFile> multipartFileList, EditGuideDTO dto, Member guide);

    void patchGuide(Long id);

    void getReject(Long id, String reject);

}

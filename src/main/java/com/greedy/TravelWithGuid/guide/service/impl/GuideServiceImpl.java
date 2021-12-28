package com.greedy.TravelWithGuid.guide.service.impl;

import com.greedy.TravelWithGuid.guide.model.dto.EditGuideDTO;
import com.greedy.TravelWithGuid.guide.model.dto.GuideDTO;
import com.greedy.TravelWithGuid.guide.model.dto.UpdateGuideDTO;
import com.greedy.TravelWithGuid.guide.model.entity.GuideAttachment;
import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import com.greedy.TravelWithGuid.guide.model.entity.GuideHistory;
import com.greedy.TravelWithGuid.guide.repository.GuideAttachmentRepository;
import com.greedy.TravelWithGuid.guide.repository.GuideHistoryRepository;
import com.greedy.TravelWithGuid.guide.repository.GuideRepository;
import com.greedy.TravelWithGuid.guide.service.GuideHistoryService;
import com.greedy.TravelWithGuid.guide.service.GuideService;
import com.greedy.TravelWithGuid.guide.service.fileUploadService;
import com.greedy.TravelWithGuid.member.model.dto.RejectGuideDTO;
import com.greedy.TravelWithGuid.guide.model.entity.Examine;
import com.greedy.TravelWithGuid.member.model.entity.Member;
import com.greedy.TravelWithGuid.guide.model.enums.Approval;
import com.greedy.TravelWithGuid.member.repository.GuideApprovalRepository;
import com.greedy.TravelWithGuid.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuideServiceImpl implements GuideService {
    private final fileUploadService uploadService;
    private final GuideRepository guideRepository;
    private final GuideHistoryService guideHistoryService;
    private final GuideHistoryRepository guideHistoryRepository;
    private final MemberRepository memberRepository;
    private final GuideApprovalRepository approvalRepository;
    private final GuideAttachmentRepository attachmentRepository;

    @Override
    public Page<GuideDTO> getGuides(String word, Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 20);
        return guideRepository.getGuides(word, pageable);
    }

    @Override
    public Page<RejectGuideDTO> getApproval(String word, Pageable pageable, String type) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 20);
        return guideRepository.getApproval(word, pageable, type);
    }

    @Override
    public boolean getGuideSignUp(List<MultipartFile> multipartFileList, EditGuideDTO dto, Member member) {
        try {
            //Guide
            Guide guide = Guide.createGuide(dto.getName(), dto.getEmail(), dto.getBank(), dto.getAccount(), dto.getIntro(), member, false);
            guideRepository.save(guide);
            //승인여부
            Examine examine = Examine.createGuide(member, guide, Approval.SUBMIT);
            approvalRepository.save(examine);
            //image
            uploadService.fileUpload(multipartFileList, guide.getId(), "GUIDE");

            return true;
        } catch (Exception e) {
            log.error("guide save error!!! " + e);
            return false;
        }
    }

    @Override
    public void patchGuide(Long id) {
        //Approval
        Examine entity = findApprovalById(id);
        entity.patchApprove(entity.getId(), Approval.APPROVE);
        approvalRepository.save(entity);
        //Guide
        Guide guide = guideId(entity.getGuide().getId());
        guide.patchGuide(guide.getId());
        guideRepository.save(guide);
        //GuideHistory
        guideHistoryService.history(guide);
        //Member
        Member member = memberId(guide.getMember().getId());
        member.patchMemberGuide(member.getId());
        memberRepository.save(member);
        //Photo
        List<GuideAttachment> a = attachmentRepository.RefNo(id);
        for (GuideAttachment photo : a) {
            GuideAttachment guideAttachment = patchGuides(photo.getId());
            guideAttachment.patchPhoto(photo.getId(), true);
            attachmentRepository.save(guideAttachment);
        }
    }

    @Override
    public void getReject(Long id, String reject) {
        //Approval
        Examine entity = findApprovalById(id);
        entity.patchReject(id, Approval.REJECT, reject);
        approvalRepository.save(entity);
    }

    @Override
    public Guide getUpdateGuide(String name) {
        Member member = findByEmail(name);
        Guide guide = guideMember(member.getId());
        return guideMember(member.getId());
    }

    //가이드 정보 수정
    @Override
    public void updateGuide(Long id, UpdateGuideDTO dto) {
        Guide guide = guideId(id);
        guide.updateGuide(guide.getId(), dto.getEmail(), dto.getBank(), dto.getAccount());
        guideRepository.save(guide);

        guideHistoryService.updateGuide(id, dto.getEmail(), dto.getBank(), dto.getAccount());
    }

    /**********************************************
     * 공통로직
     **********************************************/
    public Member memberId(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. guideId = " + id));
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Guide guideId(Long id) {
        return guideRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. guideId = " + id));
    }

    private GuideAttachment patchGuides(Long id) {
        return attachmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 사진 입니다. PhotoId = " + id));
    }

    public GuideHistory guideRefNo(Long id) {
        return guideHistoryRepository.findByRefNo(id);
    }
    public Guide guideMember(Long id) {
        return guideRepository.findByMember(id);
    }

    public GuideAttachment attachmentByRefNo(Long id) {
        return attachmentRepository.findByRefNo(id);
    }
    private Examine findApprovalById(Long id) {
        return approvalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다. id = " + id));
    }
}

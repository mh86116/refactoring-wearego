package com.greedy.TravelWithGuid.guide.service.impl;

import com.greedy.TravelWithGuid.guide.model.dto.EditGuideDTO;
import com.greedy.TravelWithGuid.guide.model.dto.GuideDTO;
import com.greedy.TravelWithGuid.guide.model.entity.Attachment;
import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import com.greedy.TravelWithGuid.guide.model.entity.GuideHistory;
import com.greedy.TravelWithGuid.guide.repository.AttachmentRepository;
import com.greedy.TravelWithGuid.guide.repository.GuideHistoryRepository;
import com.greedy.TravelWithGuid.guide.repository.GuideRepository;
import com.greedy.TravelWithGuid.guide.service.GuideService;
import com.greedy.TravelWithGuid.guide.service.fileUploadService;
import com.greedy.TravelWithGuid.member.model.dto.RejectGuideDTO;
import com.greedy.TravelWithGuid.member.model.entity.GuideApproval;
import com.greedy.TravelWithGuid.member.model.entity.Member;
import com.greedy.TravelWithGuid.member.model.enums.Approval;
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
    private final GuideHistoryRepository guideHistoryRepository;
    private final MemberRepository memberRepository;
    private final GuideApprovalRepository approvalRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public Page<GuideDTO> getGuides(String word, Pageable pageable, boolean name) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 20);
        return guideRepository.getGuides(word, pageable, name);
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
            GuideApproval approval = GuideApproval.createGuide(member, guide, Approval.SUBMIT);
            approvalRepository.save(approval);
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
        GuideApproval entity = findApprovalById(id);
        entity.patchApprove(entity.getId(), Approval.APPROVE);
        approvalRepository.save(entity);
        //Guide
        Guide guide = guideId(entity.getGuide().getId());
        guide.patchGuide(guide.getId());
        guideRepository.save(guide);
        //Member
        Member member = memberId(guide.getMember().getId());
        member.patchMemberGuide(member.getId());
        memberRepository.save(member);
        //Photo
        List<Attachment> a = attachmentRepository.RefNo(id);
        for (Attachment photo : a) {
            Attachment attachment = patchGuides(photo.getId());
            attachment.patchPhoto(photo.getId(), true);
            attachmentRepository.save(attachment);
        }
    }


    @Override
    public void getReject(Long id, String reject) {
        //Approval
        GuideApproval entity = findApprovalById(id);
        entity.patchReject(id, Approval.REJECT, reject);
        approvalRepository.save(entity);
    }

    /**********************************************
     * 공통로직
     **********************************************/
    public Member memberId(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. guideId = " + id));
    }

    public Guide guideId(Long id) {
        return guideRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. guideId = " + id));
    }

    private Attachment patchGuides(Long id) {
        return attachmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 사진 입니다. PhotoId = " + id));
    }

    public GuideHistory guideRefNo(Long id) {
        return guideHistoryRepository.findByRefNo(id);
    }

    public Attachment attachmentByRefNo(Long id) {
        return attachmentRepository.findByRefNo(id);
    }
    private GuideApproval findApprovalById(Long id) {
        return approvalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다. id = " + id));
    }
}

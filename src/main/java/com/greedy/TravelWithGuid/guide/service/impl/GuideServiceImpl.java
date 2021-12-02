package com.greedy.TravelWithGuid.guide.service.impl;

import com.greedy.TravelWithGuid.guide.model.dto.EditGuideDTO;
import com.greedy.TravelWithGuid.guide.model.dto.GuideDTO;
import com.greedy.TravelWithGuid.guide.model.entity.Attachment;
import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import com.greedy.TravelWithGuid.guide.model.entity.GuideHistory;
import com.greedy.TravelWithGuid.guide.model.enums.GuideCategory;
import com.greedy.TravelWithGuid.guide.repository.AttachmentRepository;
import com.greedy.TravelWithGuid.guide.repository.GuideHistoryRepository;
import com.greedy.TravelWithGuid.guide.repository.GuideRepository;
import com.greedy.TravelWithGuid.guide.service.GuideService;
import com.greedy.TravelWithGuid.guide.service.fileUploadService;
import com.greedy.TravelWithGuid.member.model.entity.Member;
import com.greedy.TravelWithGuid.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuideServiceImpl implements GuideService {
    private final fileUploadService uploadService;
    private final GuideRepository guideRepository;
    private final GuideHistoryRepository guideHistoryRepository;
    private final MemberRepository memberRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public Page<GuideDTO> getGuides(String word, Pageable pageable, boolean name) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 20);
        return guideRepository.getGuides(word, pageable, name);
    }

    @Override
    public boolean getGuideSignUp(List<MultipartFile> multipartFileList, EditGuideDTO dto, Member member) {

        try {
        Guide guide = Guide.createGuide(dto.getName(), dto.getEmail(), dto.getBank(), dto.getAccount(), dto.getIntro(), member, false, false);
        guideRepository.save(guide);

        List<String> history = new ArrayList<>();
        history.add(dto.getEmail());
        history.add(dto.getBank());
        history.add(dto.getAccount().replace(" ", ""));
        history.add(String.valueOf(guide.getRank()));
        history.add(String.valueOf(guide.getWarning()));

        List<GuideCategory> type = new ArrayList<>();
        type.add(GuideCategory.EMAIL);
        type.add(GuideCategory.BANK);
        type.add(GuideCategory.ACCOUNT);
        type.add(GuideCategory.RANK);
        type.add(GuideCategory.WARNING);
        //변경 이력
            for (int i = 0; i <= history.size(); i++) {
                GuideHistory history2 = GuideHistory.createHistory(history.get(i), type.get(i), guide.getId());
                guideHistoryRepository.save(history2);
            }
        //image
        uploadService.fileUpload(multipartFileList, guide.getId(), "GUIDE");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("e = " + e);
        }
        return false;
    }

    @Override
    public void patchGuide(Long id) {
        //Guide
        Guide guide = guideId(id);
        guide.patchGuide(id);
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
    public String getReject(Long id) {
        return null;
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
}

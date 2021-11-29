package com.greedy.TravelWithGuid.guide.service.impl;

import com.greedy.TravelWithGuid.guide.model.dto.EditGuideDTO;
import com.greedy.TravelWithGuid.guide.model.dto.GuideDTO;
import com.greedy.TravelWithGuid.guide.model.entity.Attachment;
import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import com.greedy.TravelWithGuid.guide.model.enums.PhotoCategory;
import com.greedy.TravelWithGuid.guide.repository.AttachmentRepository;
import com.greedy.TravelWithGuid.guide.repository.GuideRepository;
import com.greedy.TravelWithGuid.guide.service.GuideService;
import com.greedy.TravelWithGuid.member.model.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuideServiceImpl implements GuideService {
    private final GuideRepository guideRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public Page<GuideDTO> getGuides(String word, Pageable pageable, boolean name) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 20);
        return guideRepository.getGuides(word, pageable, name);
    }

    @Override
    public boolean getGuideSignUp(List<MultipartFile> multipartFileList, EditGuideDTO dto, Member member) {
        Guide guide = Guide.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .bank(dto.getBank().replace(" ", ""))
                .account(dto.getAccount().replace(" ", ""))
                .intro(dto.getIntro())
                .member(member)
                .approvalYn(false)
                .isEnable(false)
                .build();
        guideRepository.save(guide);
        /*******************************************************************
         * image save
         ******************************************************************/
        for (MultipartFile file : multipartFileList) {
            int dot = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
            String ext = file.getOriginalFilename().substring(dot);
            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

            File filePath = new File(System.getProperty("user.dir") + "/src/main/resources/static/img/upload/guide");
            if (!filePath.exists()) {
                System.out.println("폴더 생성 = " + filePath.mkdirs());
            }

            File filePath2 = new File(filePath + "/" + savedName);
            try {
                FileOutputStream newFilePath = new FileOutputStream(filePath2, true);
                newFilePath.write(file.getBytes());

                Attachment attachment = Attachment.builder()
                        .category(PhotoCategory.GUIDE)
                        .originalName(file.getOriginalFilename())
                        .savedName(savedName)
                        .savePath(filePath.getPath())
                        .isEnable(false)
                        .build();
                attachmentRepository.save(attachment);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

}

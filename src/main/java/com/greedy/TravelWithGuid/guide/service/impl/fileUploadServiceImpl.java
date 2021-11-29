package com.greedy.TravelWithGuid.guide.service.impl;

import com.greedy.TravelWithGuid.guide.model.entity.Attachment;
import com.greedy.TravelWithGuid.guide.model.enums.PhotoCategory;
import com.greedy.TravelWithGuid.guide.repository.AttachmentRepository;
import com.greedy.TravelWithGuid.guide.service.fileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class fileUploadServiceImpl implements fileUploadService {
    private final AttachmentRepository attachmentRepository;

    @Override
    public void fileUpload(List<MultipartFile> multipartFile) {
//            int dot = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
//            String ext = file.getOriginalFilename().substring(dot);
//            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
//
//            File filePath = new File(System.getProperty("user.dir") + "/src/main/resources/static/img/upload");
//            if (!filePath.exists()) {
//                System.out.println("폴더 생성 = " + filePath.mkdirs());
//            }
//            File filePath2 = new File(filePath + "/" + savedName);
//            try {
//                FileOutputStream newFilePath = new FileOutputStream(filePath2, true);
//                newFilePath.write(file.getBytes());
//
//                Attachment attachment = Attachment.builder()
//                        .category(PhotoCategory.GOODS)
//                        .originalName(file.getOriginalFilename())
//                        .savedName(savedName)
//                        .savePath(filePath.getPath())
//                        .isEnable(false)
//                        .build();
//                attachmentRepository.save(attachment);
//                System.out.println("attachment = " + attachment);
//            } catch (IOException e) {
//                e.printStackTrace();
//        }
    }

}

package com.greedy.TravelWithGuid.guide.service.impl;

import com.greedy.TravelWithGuid.guide.model.entity.Attachment;
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

    public void fileUpload(List<MultipartFile> multipartFileList, Long id, String name) {
        for (MultipartFile file : multipartFileList) {
            int dot = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
            String ext = file.getOriginalFilename().substring(dot);
            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
            File filePath1 = new File(System.getProperty("user.dir") + "/src/main/resources/static/img/upload");
            File filePath2 = null;
            File filePath3 = null;
            
            if (!filePath1.exists()) {
                System.out.println("폴더 생성 path1 = " + filePath1.mkdirs());
            }
            if (name.equals("GUIDE")) {
                filePath2 = new File(filePath1 + "/guide");
                if (!filePath2.exists()) {
                    System.out.println("폴더 생성 path2 = " + filePath2.mkdirs());
                }
                filePath2 = new File(filePath2 + "/" + savedName);
                filePath3 = new File("/img/upload/guide" + "/" + savedName);
            } else {
                filePath2 = new File(filePath1 + "/goods");
                if (!filePath2.exists()) {
                    System.out.println("폴더 생성 path2 = " + filePath2.mkdirs());
                }
                filePath2 = new File(filePath2 + "/" + savedName);
                filePath3 = new File("/img/upload/goods" + "/" + savedName);
            }
            try {
                FileOutputStream newFilePath = new FileOutputStream(filePath2, true);
                newFilePath.write(file.getBytes());
                Attachment attachment = Attachment.create(id, file.getOriginalFilename(), savedName, filePath3.getPath(), name);
                attachmentRepository.save(attachment);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
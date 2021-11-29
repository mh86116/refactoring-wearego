package com.greedy.TravelWithGuid.guide.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface fileUploadService {
    /**
     * fileUpload
     */
    void fileUpload(List<MultipartFile> multipartFile);
}

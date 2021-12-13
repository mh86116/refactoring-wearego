package com.greedy.TravelWithGuid.guide.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface fileUploadService {

    void fileUpload(List<MultipartFile> multipartFileList, Long id, String name);
}

package com.greedy.TravelWithGuid.guide.service;

import com.greedy.TravelWithGuid.guide.model.dto.GoodsDTO;
import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GoodsService {
    boolean goodsFileUpload(List<MultipartFile> multipartFile, GoodsDTO dto, List<String> optionName, List<String> optionPrice, Guide guide);

}
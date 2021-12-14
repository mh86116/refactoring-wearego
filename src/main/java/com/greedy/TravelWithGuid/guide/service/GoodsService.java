package com.greedy.TravelWithGuid.guide.service;

import com.greedy.TravelWithGuid.guide.model.dto.EditGoodsDTO;
import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GoodsService {
    boolean goodsFileUpload(List<MultipartFile> multipartFile, EditGoodsDTO dto, List<String> optionName, List<String> optionPrice, Guide guide);

    Object getGoodsList(String word, Pageable pageable);
}

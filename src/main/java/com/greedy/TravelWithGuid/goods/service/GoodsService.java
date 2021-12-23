package com.greedy.TravelWithGuid.goods.service;

import com.greedy.TravelWithGuid.goods.model.dto.EditGoodsDTO;
import com.greedy.TravelWithGuid.goods.model.dto.GoodsDTO;
import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GoodsService {
    boolean goodsFileUpload(List<MultipartFile> multipartFile, EditGoodsDTO dto, List<String> optionName, List<String> optionPrice, Guide guide);

    Page<GoodsDTO> getGoodsList(String word, Pageable pageable);

    void patchGoods(Long id);

    void getReject(Long id, String reject);
}

package com.greedy.TravelWithGuid.guide.service.impl;

import com.greedy.TravelWithGuid.guide.model.dto.EditGoodsDTO;
import com.greedy.TravelWithGuid.guide.model.dto.GoodsDTO;
import com.greedy.TravelWithGuid.guide.model.entity.Goods;
import com.greedy.TravelWithGuid.guide.model.entity.GoodsOption;
import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import com.greedy.TravelWithGuid.guide.repository.GoodsOptionRepository;
import com.greedy.TravelWithGuid.guide.repository.GoodsRepository;
import com.greedy.TravelWithGuid.guide.service.GoodsService;
import com.greedy.TravelWithGuid.guide.service.fileUploadService;
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
public class GoodsServiceImpl implements GoodsService {
    private final fileUploadService uploadService;
    private final GoodsRepository goodsRepository;
    private final GoodsOptionRepository optionRepository;

    @Override
    public boolean goodsFileUpload(List<MultipartFile> multipartFile, EditGoodsDTO dto, List<String> optionName, List<String> optionPrice, Guide guide) {
        Goods goods = Goods.createGoods(guide, dto.getTitle(), dto.getPrice(), dto.getPlace(), dto.getStartDt(), dto.getEndDt(),
                dto.getPerson(), dto.getBody(), false);
        goodsRepository.save(goods);
        if (!goods.isNew()) {
            //option
            String name = "";
            String price = "";
            for (int i = 0; i < optionName.size(); i++) {
                name = optionName.get(i);
                for (int j = 0; j < optionPrice.size(); j++) {
                    price = optionPrice.get(i);
                }
                GoodsOption option = GoodsOption.create(goods, name, price);
                optionRepository.save(option);
            }
            //image
            uploadService.fileUpload(multipartFile, guide.getId(), "GUIDE");
        }
        return true;
    }

    @Override
    public Page<GoodsDTO> getGoodsList(String word, Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 20);
        return goodsRepository.getGoodsList(word, pageable);
    }
}
package com.greedy.TravelWithGuid.goods.service.impl;

import com.greedy.TravelWithGuid.goods.model.dto.EditGoodsDTO;
import com.greedy.TravelWithGuid.goods.model.dto.GoodsDTO;
import com.greedy.TravelWithGuid.goods.model.entity.Goods;
import com.greedy.TravelWithGuid.goods.model.entity.GoodsOption;
import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import com.greedy.TravelWithGuid.goods.repository.GoodsOptionRepository;
import com.greedy.TravelWithGuid.goods.repository.GoodsRepository;
import com.greedy.TravelWithGuid.goods.service.GoodsService;
import com.greedy.TravelWithGuid.guide.model.enums.Approval;
import com.greedy.TravelWithGuid.guide.service.fileUploadService;
import com.greedy.TravelWithGuid.guide.model.entity.Examine;
import com.greedy.TravelWithGuid.member.repository.GuideApprovalRepository;
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
    private final GuideApprovalRepository approvalRepository;

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
                GoodsOption option = GoodsOption.create(goods.getId(), name, price);
                optionRepository.save(option);
            }
            //image
            uploadService.fileUpload(multipartFile, guide.getId(), "GOODS");
        }
        return true;
    }

    @Override
    public Page<GoodsDTO> getGoodsList(String word, Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 20);
        return goodsRepository.getGoodsList(word, pageable);
    }

    @Override
    public void patchGoods(Long id) {
        //Approval
        Examine entity = findApprovalById(id);
        entity.patchApprove(entity.getId(), Approval.APPROVE);
        approvalRepository.save(entity);
        //Goods
        Goods goods = goodsId(entity.getGuide().getId());


    }


    @Override
    public void getReject(Long id, String reject) {

    }

    /************
     * 공통 로직
     ***********/
    private Examine findApprovalById(Long id) {
        return approvalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다. id = " + id));
    }

    private Goods goodsId(Long id) {
        return goodsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품 입니다. id = " + id));
    }
}
package com.greedy.TravelWithGuid.guide.controller;

import com.greedy.TravelWithGuid.guide.model.dto.EditGoodsDTO;
import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import com.greedy.TravelWithGuid.guide.repository.GuideRepository;
import com.greedy.TravelWithGuid.guide.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/goods")
public class GoodsController {
    private final GoodsService goodsService;
    private final GuideRepository guideRepository;

    /*******************************************
     * EDIT GOODS
     ******************************************/

    @GetMapping("/editGoods")
    public String editGoods() {
        return "guide/goods/editGoods";
    }

    @PostMapping("/editGoods")
    public String editFileUpload(@RequestParam("file") List<MultipartFile> multipartFile, @ModelAttribute EditGoodsDTO dto,
                                 @RequestParam(required = false) List<String> optionName, @RequestParam(required = false) List<String> optionPrice,
                                 Principal principal, Model model) throws IOException {

        Guide guide = guideId(principal.getName());
        boolean result = goodsService.goodsFileUpload(multipartFile, dto, optionName, optionPrice, guide);
        System.out.println("result = " + result);
        if (result) {
            model.addAttribute("msg", "등록 승인 대기중입니다. 승인이 완료될 때까지 기다려 주세요.");
            model.addAttribute("movePath", "/goods/editGoods");
            return "cmmn/success";
        } else {
            model.addAttribute("msg", "등록이 실패 되었습니다. 다시 확인해주세요.");
            model.addAttribute("movePath", "/");
            return "cmmn/fail";
        }
//        return null;
    }


    /************
     * 공통 로직
     ***********/
    public Guide guideId(String email) {
        return guideRepository.findByEmail(email);
    }
}

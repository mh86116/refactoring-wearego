package com.greedy.TravelWithGuid.admin.controller;

import com.greedy.TravelWithGuid.goods.service.GoodsService;
import com.greedy.TravelWithGuid.guide.service.GuideService;
import com.greedy.TravelWithGuid.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class adminController {
    private final MemberService memberService;
    private final GuideService guideService;
    private final GoodsService goodsService;

    @GetMapping("/")
    public String adminMain() {
        return "admin/cmmn/main";
    }

    /******************************************************
     * MEMBER
     *****************************************************/
    @GetMapping("/members")
    public String members(Model model, @RequestParam(value = "word", required = false) String word,
                          @PageableDefault Pageable pageable, @RequestParam(required = false) boolean paging) {
        model.addAttribute("members", memberService.getMembers(word, pageable));
        model.addAttribute("word", word);

        if (StringUtils.hasText(word) && !paging) {
            return "admin/member/members :: #memberTable";
        }
        return "admin/member/members";
    }

    /******************************************************
     * GUIDE
     *****************************************************/
    //가이드 신청 리스트
    @GetMapping("/waitingGuides")
    public String waitingGuides(Model model, @RequestParam(value = "word", required = false) String word,
                         @PageableDefault Pageable pageable, @RequestParam(required = false) boolean paging) {
        model.addAttribute("word", word);
        model.addAttribute("guides", guideService.getGuideApproval(word, pageable, "SUBMIT"));
        if (StringUtils.hasText(word) && !paging) {
            return "admin/guide/waitingGuides :: #guideTable";
        } else {
            return "admin/guide/waitingGuides";
        }
    }
    //승인된 가이드 리스트
    @GetMapping("/waiteGuide")
    public String waiteGuides(Model model, @RequestParam(value = "word", required = false) String word,
                              @PageableDefault Pageable pageable, @RequestParam(required = false) boolean paging) {
        model.addAttribute("word", word);
        model.addAttribute("approval", guideService.getApproval(word, pageable, "APPROVE"));
        if (StringUtils.hasText(word) && !paging) {
            return "admin/member/waiteGuide :: #memberTable";
        } else {
            return "admin/member/waiteGuide";
        }
    }
    //반려된 가이드 리스트
    @GetMapping("/rejectGuide")
    public String rejectGuide(Model model, @RequestParam(value = "word", required = false) String word,
                              @PageableDefault Pageable pageable, @RequestParam(required = false) boolean paging) {
        model.addAttribute("word", word);
        model.addAttribute("approval", guideService.getApproval(word, pageable, "REJECT"));
        if (StringUtils.hasText(word) && !paging) {
            return "admin/member/rejectGuide :: #memberTable";
        } else {
            return "admin/member/rejectGuide";
        }
    }

    //승인 여부
    @ResponseBody
    @PatchMapping("/guide/{id}")
    public String patchGuide(@PathVariable Long id, @RequestParam String value, @RequestParam(value = "reject", required = false) String reject) {
        if (value.equals("approval")) {
        guideService.patchGuide(id);
        } else {
            guideService.getReject(id, reject);
        }
        return "ok";
    }

    /******************************************************
     * GOODS
     *****************************************************/
    @GetMapping("/goods")
    public String goodsList(Model model, @RequestParam(value = "word", required = false) String word,
                            @PageableDefault Pageable pageable, @RequestParam(required = false) boolean paging) {
        model.addAttribute("word", word);
        model.addAttribute("goods", goodsService.getGoodsList(word, pageable));
        System.out.println("model = " + model);
        if (StringUtils.hasText(word) && !paging) {
            return "admin/goods/goodsList :: #goodsTable";
        } else {
            return "admin/goods/goodsList";
        }
    }

    @ResponseBody
    @PatchMapping("/goods/{id}")
    public String patchGoods(@PathVariable Long id, @RequestParam String value, @RequestParam(value = "reject", required = false) String reject) {
        System.out.println("value = " + value);
        System.out.println("reject = " + reject);
        if (value.equals("approval")) {
            goodsService.patchGoods(id);
        } else {
            goodsService.getReject(id, reject);
        }
        return "ok";
    }

    /******************************************************
     * BOARD
     *****************************************************/
    @GetMapping("/board/beginner")
    public String beginner() { return "admin/board/beginner"; }

    @GetMapping("/board/questions")
    public String questions() { return "admin/board/questions"; }

}

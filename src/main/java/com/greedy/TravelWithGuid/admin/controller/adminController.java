package com.greedy.TravelWithGuid.admin.controller;

import com.greedy.TravelWithGuid.guide.service.GoodsService;
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
    @GetMapping("/guides")
    public String guides(Model model, @RequestParam(value = "word", required = false) String word,
                         @PageableDefault Pageable pageable, @RequestParam(required = false) boolean paging) {
        model.addAttribute("word", word);
        model.addAttribute("guides", guideService.getGuides(word, pageable, true));
        if (StringUtils.hasText(word) && !paging) {
            return "admin/guide/guides :: #guideTable";
        } else {
            return "admin/guide/guides";
        }
    }

    @GetMapping("/waitingGuides")
    public String waitingGuides(Model model, @RequestParam(value = "word", required = false) String word,
                         @PageableDefault Pageable pageable, @RequestParam(required = false) boolean paging) {
        model.addAttribute("word", word);
        model.addAttribute("guides", guideService.getGuides(word, pageable, false));
        if (StringUtils.hasText(word) && !paging) {
            return "admin/guide/waitingGuides :: #guideTable";
        } else {
            return "admin/guide/waitingGuides";
        }
    }

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

    @GetMapping("/waiteGuide")
    public String waiteGuides(Model model, @RequestParam(value = "word", required = false) String word,
                              @PageableDefault Pageable pageable, @RequestParam(required = false) boolean paging) {
        model.addAttribute("word", word);
        model.addAttribute("approval", guideService.getApproval(word, pageable, "SUBMIT"));
        if (StringUtils.hasText(word) && !paging) {
            return "admin/member/waiteGuide :: #memberTable";
        } else {
            return "admin/member/waiteGuide";
        }
    }

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

    /******************************************************
     * GOODS
     *****************************************************/
    @GetMapping("/goodsList")
    public String goodsList(Model model, @RequestParam(value = "word", required = false) String word,
                            @PageableDefault Pageable pageable, @RequestParam(required = false) boolean paging) {
        model.addAttribute("word", word);
        model.addAttribute("goodsList", goodsService.getGoodsList(word, pageable));
        if (StringUtils.hasText(word) && !paging) {
            return "admin/goods/goodsList :: #goodsTable";
        } else {
            return "admin/goods/goodsList";
        }
    }

    /******************************************************
     * BOARD
     *****************************************************/
    @GetMapping("/board/beginner")
    public String beginner() { return "admin/board/beginner"; }

    @GetMapping("/board/questions")
    public String questions() { return "admin/board/questions"; }

}

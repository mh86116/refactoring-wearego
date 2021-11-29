package com.greedy.TravelWithGuid.admin.controller;

import com.greedy.TravelWithGuid.guide.service.GuideService;
import com.greedy.TravelWithGuid.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class adminController {
    private final MemberService memberService;
    private final GuideService guideService;

    @GetMapping("/")
    public String adminMain() { return "admin/cmmn/main"; }

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
}

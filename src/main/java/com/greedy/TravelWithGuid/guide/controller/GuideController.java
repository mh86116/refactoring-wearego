package com.greedy.TravelWithGuid.guide.controller;

import com.greedy.TravelWithGuid.guide.model.dto.EditGuideDTO;
import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import com.greedy.TravelWithGuid.guide.repository.GuideRepository;
import com.greedy.TravelWithGuid.guide.service.GuideService;
import com.greedy.TravelWithGuid.member.model.entity.Member;
import com.greedy.TravelWithGuid.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/guide")
public class GuideController {
    private final GuideService guideService;
    private final MemberRepository memberRepository;

    @GetMapping("/editGuide")
    public String editGuide() { return "guide/editGuide";}

    @PostMapping("/editGuide")
    public String signUpGuide(@RequestParam("file") List<MultipartFile> multipartFileList, @ModelAttribute EditGuideDTO dto,
                              Principal principal, Model model) {
        Member member = memberId(principal.getName());
        boolean result = guideService.getGuideSignUp(multipartFileList, dto, member);
        if (result) {
            model.addAttribute("msg", "신청 승인 대기중입니다. 승인이 완료될 때까지 기다려 주세요.");
            model.addAttribute("movePath", "/goods/editGoods");
            return "cmmn/success";
        } else {
            model.addAttribute("msg", "신청이 실패 되었습니다. 다시 확인해주세요.");
            model.addAttribute("movePath", "/");
            return "cmmn/fail";
        }
//        return null;
    }

    /************
     * 공통 로직
     ***********/
    public Member memberId(String email) {
        return memberRepository.findByEmail(email);
    }
}

package com.greedy.TravelWithGuid.member.controller;

import com.greedy.TravelWithGuid.guide.model.dto.UpdateGuideDTO;
import com.greedy.TravelWithGuid.member.model.dto.MemberCheckDTO;
import com.greedy.TravelWithGuid.member.repository.MemberRepository;
import com.greedy.TravelWithGuid.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    /*******************************************
     * MY PAGE
     ******************************************/
    @GetMapping("/myPage")
    public String myPage() { return "member/myPage"; }

    @GetMapping("/update")
    public String update(Model model, Principal principal) {
        model.addAttribute("member", memberService.updateMember(principal.getName()));
        return "member/update"; }

    @ResponseBody
    @PatchMapping("/update/{id}")
    public String patchUpdate(@PathVariable Long id, @ModelAttribute UpdateGuideDTO dto) {
        memberService.patchUpdate(id, dto);
        return "ok";
    }

    @GetMapping("/pwdUpdate")
    public String pwdUpdate(Model model, Principal principal) {
        model.addAttribute("member", memberService.updateMember(principal.getName()));
        return "member/pwdUpdate"; }

    @ResponseBody
    @PatchMapping("/pwdUpdate/{id}")
    public String patchPwdUpdate(@PathVariable Long id, @RequestParam String pwd) {
        System.out.println("pwd = " + pwd);
        memberService.patchPwdUpdate(id, pwd);
        return "ok";
    }


}

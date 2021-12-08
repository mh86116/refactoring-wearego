package com.greedy.TravelWithGuid.member.controller;

import com.greedy.TravelWithGuid.guide.model.dto.UpdateGuideDTO;
import com.greedy.TravelWithGuid.member.model.dto.MemberCheckDTO;
import com.greedy.TravelWithGuid.member.model.dto.MemberDeleteDTO;
import com.greedy.TravelWithGuid.member.repository.MemberRepository;
import com.greedy.TravelWithGuid.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    /*******************************************
     * GET
     ******************************************/
    @GetMapping("/myPage")
    public String myPage() { return "member/myPage"; }

    @GetMapping("/check")
    public String check() { return "member/check"; }

    @GetMapping("/update")
    public String update(Model model, Principal principal) {
        model.addAttribute("member", memberService.updateMember(principal.getName()));
        return "member/update";
    }

    @GetMapping("/pwdUpdate")
    public String pwdUpdate(Model model, Principal principal) {
        model.addAttribute("member", memberService.updateMember(principal.getName()));
        return "member/pwdUpdate";
    }

    @GetMapping("/delete")
    public String delete(Model model, Principal principal) {
        model.addAttribute("member", memberService.updateMember(principal.getName()));
        return "member/delete";
    }

    /*******************************************
     * PATCH & POST
     ******************************************/
    @PostMapping("/check")
    public String check(@ModelAttribute MemberCheckDTO dto, Model model) {
        boolean result = memberService.checkResult(dto);
        if (result) {
            model.addAttribute("msg", "회원 정보 수정이 가능합니다.");
            model.addAttribute("movePath", "/member/update");
            return "cmmn/success";
        } else {
            model.addAttribute("msg", "비밀번호가 맞지 않습니다.");
            model.addAttribute("movePath", "/member/check");
            return "cmmn/fail";
        }
    }

    @ResponseBody
    @PatchMapping("/update/{id}")
    public String patchUpdate(@PathVariable Long id, @ModelAttribute UpdateGuideDTO dto) {
        memberService.patchUpdate(id, dto);
        return "ok";
    }

    @ResponseBody
    @PatchMapping("/pwdUpdate/{id}")
    public String patchPwdUpdate(@PathVariable Long id, @RequestParam String pwd) {
        memberService.patchPwdUpdate(id, pwd);
        return "ok";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute MemberDeleteDTO dto, Model model) {
        boolean result = memberService.delete(dto);
        if (result) {
            model.addAttribute("msg", "탈퇴가 완료 되었습니다. 그동안 이용해주셔서 감사합니다.");
            model.addAttribute("movePath", "/logout");
            return "cmmn/success";
        } else {
            model.addAttribute("msg", "비밀번호가 맞지 않습니다.");
            model.addAttribute("movePath", "/member/delete");
            return "cmmn/fail";
        }
    }
}

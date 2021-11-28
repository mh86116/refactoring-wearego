package com.greedy.TravelWithGuid.member.controller;

import com.greedy.TravelWithGuid.member.repository.MemberRepository;
import com.greedy.TravelWithGuid.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}

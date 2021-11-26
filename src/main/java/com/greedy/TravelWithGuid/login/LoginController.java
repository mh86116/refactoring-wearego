package com.greedy.TravelWithGuid.login;

import com.greedy.TravelWithGuid.login.model.dto.JoinDTO;
import com.greedy.TravelWithGuid.login.service.LoginService;
import com.greedy.TravelWithGuid.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/join")
    public String join() {
        return "login/join";
    }

    @GetMapping("/login")
    public String login() {
        return "index";
    }

    @PostMapping("/join")
    public String signUp(@ModelAttribute JoinDTO dto) {
        if (loginService.join(dto)) {
            return "redirect:/";
        }
        return "cmmn/error";
    }

    @GetMapping("/error")
    public String error() { return "cmmn/error"; }
}

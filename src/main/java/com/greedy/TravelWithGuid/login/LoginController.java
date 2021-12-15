package com.greedy.TravelWithGuid.login;

import com.greedy.TravelWithGuid.login.model.dto.JoinDTO;
import com.greedy.TravelWithGuid.login.service.LoginService;
import com.greedy.TravelWithGuid.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/joins")
    @ResponseBody
    public boolean join(@RequestParam(value = "email", required = false) String email,
                       @RequestParam(value = "nickname", required = false) String nickname) {
        if (email != null) {
            return loginService.checkEmail(email);
        } else if (nickname != null) {
            return loginService.checkNickname(nickname.replace(" ", ""));
        }
        return false;
    }

    @GetMapping("/join")
    public String joins() { return "login/join"; }

    @GetMapping("/login")
    public String login() {
        return "index";
    }

    @PostMapping("/join")
    public String signUp(@ModelAttribute JoinDTO dto) {
        if (loginService.join(dto)) {
            return "redirect:/";
        }
        return "login/join";
    }

    @GetMapping("/error")
    public String error() {
        return "cmmn/error";
    }
}

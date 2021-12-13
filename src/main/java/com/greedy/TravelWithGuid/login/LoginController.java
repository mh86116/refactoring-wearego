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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String join(Model model, @RequestParam(value = "email", required = false) String email,
                       @RequestParam(value = "nickname", required = false) String nickname) {
        if (email != null) {
        boolean result = loginService.checkEmail(email);
            model.addAttribute("email", result);
            System.out.println("model = " + model);
            return "/login/join";
        } else if (nickname != null) {
        boolean result = loginService.checkNickname(nickname);
            model.addAttribute("nickname", result);
            System.out.println("model = " + model);
            return "/login/join";
        }
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
        return "login/join";
    }

    @GetMapping("/error")
    public String error() {
        return "cmmn/error";
    }
}

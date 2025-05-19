package com.goormitrip.goormitrip.controller;

import com.goormitrip.goormitrip.dto.SignupRequestDto;
import com.goormitrip.goormitrip.repository.UserRepository;
import com.goormitrip.goormitrip.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class SignupController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private UserService userService;

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(SignupRequestDto signupRequestDto) {
        userService.processSignup(signupRequestDto);
        userRepository.save(signupRequestDto.toUser(passwordEncoder));
        return "redirect:/login";
    }
}

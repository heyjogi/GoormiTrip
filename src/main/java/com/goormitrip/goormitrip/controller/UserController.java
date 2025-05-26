package com.goormitrip.goormitrip.controller;

import com.goormitrip.goormitrip.domain.UserEntity;
import com.goormitrip.goormitrip.dto.AuthRequest;
import com.goormitrip.goormitrip.dto.SignupRequest;
import com.goormitrip.goormitrip.security.JwtUtils;
import com.goormitrip.goormitrip.service.AuthService;
import com.goormitrip.goormitrip.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final UserDetails userDetails;
    private final JwtUtils jwtUtils;

    @PostMapping("/signup")
    public SignupRequest signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return request;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다."));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(user.getPassword(), request.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다.");
        }
        return jwtUtils.generateToken(authService.login(request));
    }
}

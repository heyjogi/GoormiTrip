package com.goormitrip.goormitrip.user.service;

import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.user.domain.UserRole;
import com.goormitrip.goormitrip.user.dto.AuthRequest;
import com.goormitrip.goormitrip.user.dto.AuthResponse;
import com.goormitrip.goormitrip.user.dto.SignupRequest;
import com.goormitrip.goormitrip.user.repository.UserRepository;
import com.goormitrip.goormitrip.global.security.JwtUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @Transactional
    public AuthResponse signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("이미 가입된 회원입니다.");
        }

        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setRole(UserRole.USER);

        userRepository.save(user);

        // Authentication authentication = authenticationManager.authenticate(
        //     new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        // );

        // SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = new CustomUserDetails(user);
        String jwt = jwtUtils.generateToken(userDetails);

        return new AuthResponse(jwt, user.getEmail(), user.getRole().name());
    }

    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);

        UserEntity user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("이메일 또는 비밀번호가 잘못되었습니다."));

        return new AuthResponse(jwt, user.getEmail(), user.getRole().name());
    }

}

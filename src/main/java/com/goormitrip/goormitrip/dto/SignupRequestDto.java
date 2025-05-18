package com.goormitrip.goormitrip.dto;

import com.goormitrip.goormitrip.domain.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Data
public class SignupRequestDto {

    private String email;
    private String password;
    private String phone;
    private LocalDateTime createdAt;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(email)
                .phone(phone)
                .password(passwordEncoder.encode(password))
                .build();
    }
}

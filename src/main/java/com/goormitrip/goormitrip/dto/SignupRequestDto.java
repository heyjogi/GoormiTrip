package com.goormitrip.goormitrip.dto;

import com.goormitrip.goormitrip.domain.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Data
public class SignupRequestDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "전화번호를 입력해주세요.")
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

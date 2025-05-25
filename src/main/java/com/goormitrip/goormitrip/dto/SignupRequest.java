package com.goormitrip.goormitrip.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {

    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "이메일 형식을 확인해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(min = 8, max = 20, message = "비밀번호를 다시 입력하세요.")
    private String password;

    @NotBlank(message = "전화번호를 입력하세요.")
    @Pattern(regexp = "^01(0|1|[6-9])[0-9]{3,4}[0-9]{4}$", message = "숫자만 입력하세요.")
    private String phone;


}

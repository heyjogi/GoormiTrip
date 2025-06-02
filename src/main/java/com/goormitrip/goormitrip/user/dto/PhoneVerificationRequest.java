package com.goormitrip.goormitrip.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PhoneVerificationRequest {
	@NotBlank(message = "전화번호를 입력하세요.")
	@Pattern(regexp = "^01(0|1|[6-9])[0-9]{3,4}[0-9]{4}$", message = "숫자만 입력하세요.")
	private String phone;
}

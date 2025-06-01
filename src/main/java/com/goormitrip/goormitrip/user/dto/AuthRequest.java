package com.goormitrip.goormitrip.user.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {

	@NotBlank(message = "이메일을 입력하세요.")
	@Email(message = "이메일 또는 비밀번호가 잘못되었습니다.")
	private String email;

	@NotBlank(message = "비밀번호를 입력하세요.")
	private String password;

	@Id
	private String phoneNumber;

	private boolean verified;

	public AuthRequest() {
	}

	public AuthRequest(String phoneNumber, boolean verified) {
		this.phoneNumber = phoneNumber;
		this.verified = verified;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
}

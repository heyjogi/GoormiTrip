package com.goormitrip.goormitrip.user.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goormitrip.goormitrip.user.domain.AuthRequestEntity;
import com.goormitrip.goormitrip.user.repository.AuthRequestRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/verification/phone")
@RequiredArgsConstructor
public class PhoneVerificationController {

	private final AuthRequestRepository authRequestRepository;

	@PostMapping("/request")
	public ResponseEntity<String> request(@RequestParam String phone) {
		if(isInvalidPhoneNumber(phone)) {
			return ResponseEntity.badRequest().body("휴대폰 번호를 확인해주세요.");
		}

		authRequestRepository.save(new AuthRequestEntity(phone, false));
		return ResponseEntity.ok("인증 번호가 생성되었습니다.");
	}

	@GetMapping("/verify")
	public ResponseEntity<String> verify(@RequestParam String phone) {
		if(isInvalidPhoneNumber(phone)) {
			return ResponseEntity.badRequest().body("휴대폰 번호를 확인해주세요.");
		}

		return authRequestRepository.findById(phone)
			.filter(AuthRequestEntity::isVerified)
			.map(a -> ResponseEntity.ok("인증되었습니다."))
			.orElse(ResponseEntity.status(401).body("인증에 실패하였습니다."));

	}

	private boolean isInvalidPhoneNumber(String phone) {
		return phone == null || !phone.matches("^01[016789][0-9]{7,8}$");
	}
}

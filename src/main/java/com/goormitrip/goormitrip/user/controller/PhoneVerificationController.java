package com.goormitrip.goormitrip.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goormitrip.goormitrip.user.domain.PhoneVerification;
import com.goormitrip.goormitrip.user.dto.PhoneVerificationRequest;
import com.goormitrip.goormitrip.user.exception.InvalidPhoneException;
import com.goormitrip.goormitrip.user.exception.PhoneVerificationFailedException;
import com.goormitrip.goormitrip.user.repository.AuthRequestRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/verification/phone")
@RequiredArgsConstructor
public class PhoneVerificationController {

	private final AuthRequestRepository authRequestRepository;

	@PostMapping("/request")
	public ResponseEntity<String> request(@RequestBody @Valid PhoneVerificationRequest request) {
		String phone = request.getPhone();
		if(isInvalidPhoneNumber(phone)) {
			throw new InvalidPhoneException();
		}

		authRequestRepository.save(new PhoneVerification(phone, false));
		return ResponseEntity.ok("인증 번호가 생성되었습니다.");
	}

	@GetMapping("/verify")
	public ResponseEntity<String> verify(@RequestParam("phone") String phone) {
		if (isInvalidPhoneNumber(phone)) {
			throw new InvalidPhoneException();
		}

		PhoneVerification verification = authRequestRepository.findById(phone)
			.orElseThrow(PhoneVerificationFailedException::new);

		if (!verification.isVerified()) {
			throw new PhoneVerificationFailedException();
		}

		return ResponseEntity.ok("인증되었습니다.");
	}

	private boolean isInvalidPhoneNumber(String phone) {
		return phone == null || !phone.matches("^01(0|1|[6-9])[0-9]{3,4}[0-9]{4}$");
	}
}

package com.goormitrip.goormitrip.user.controller;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormitrip.goormitrip.user.repository.AuthRequestRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoWebhookController {

	private final AuthRequestRepository authRequestRepository;

	@PostMapping("/webhook")
	public ResponseEntity<Map<String, Object>> receive(@RequestBody Map<String, Object> body) {
		try {
			Map<String, Object> userRequest = (Map<String, Object>) body.get("userRequest");
			String utterance = (String) userRequest.get("utterance");

			if (utterance != null && utterance.startsWith("인증")) {
				String phone = utterance.replaceAll("[^0-9]","");

				authRequestRepository.findById(phone).ifPresent((auth -> {
					auth.setVerified(true);
					authRequestRepository.save(auth);
				}));

				return responseMessage("인증되었습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseMessage("인증에 실패하였습니다.");
	}

	private ResponseEntity<Map<String, Object>> responseMessage(String text) {
		Map<String, Object> response = new HashMap<>();
		response.put("version", "2.0");

		Map<String, Object> template = new HashMap<>();
		Map<String, Object> simpleText = new HashMap<>();
		simpleText.put("text", text);

		template.put("outputs", List.of(Map.of("simpleText", simpleText)));
		response.put("template", template);

		return ResponseEntity.ok(response);
	}

}

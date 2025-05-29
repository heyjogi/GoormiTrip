package com.goormitrip.goormitrip.global.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HealthController {

	@GetMapping("/health")
	public String healthCheck() {
		log.info("Health check endpoint hit");
		return "ok";
	}
}

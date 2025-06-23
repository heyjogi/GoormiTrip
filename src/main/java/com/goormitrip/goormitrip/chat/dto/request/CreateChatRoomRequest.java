package com.goormitrip.goormitrip.chat.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record CreateChatRoomRequest(
	@JsonProperty("inquiry_type")
	String inquiryType,

	@NotBlank
	@JsonProperty("initial_message")
	String initialMessage
) {}
package com.goormitrip.goormitrip.chat.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SendMessageRequest(@NotBlank String message) {
}
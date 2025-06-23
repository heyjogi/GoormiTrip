package com.goormitrip.goormitrip.user.dto;

public record PasswordResetConfirmRequest(
	String reset_token,
	String new_password
) { }

package com.goormitrip.goormitrip.user.service;

public interface PasswordResetService {
	void requestReset(String email);
	void resetPassword(String resetToken, String newPassword);
}

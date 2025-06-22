package com.goormitrip.goormitrip.user.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.user.exception.EmailDuplicateException;
import com.goormitrip.goormitrip.user.exception.InvalidEmailFormatException;
import com.goormitrip.goormitrip.user.exception.InvalidPasswordException;
import com.goormitrip.goormitrip.user.exception.InvalidPhoneException;
import com.goormitrip.goormitrip.user.exception.InvalidResetTokenException;
import com.goormitrip.goormitrip.user.exception.LoginFailedException;
import com.goormitrip.goormitrip.user.exception.MailSendFailedException;
import com.goormitrip.goormitrip.user.exception.PhoneNotVerifiedException;
import com.goormitrip.goormitrip.user.exception.PhoneVerificationFailedException;
import com.goormitrip.goormitrip.user.exception.RequiredTermsUncheckedException;
import com.goormitrip.goormitrip.user.exception.SocialLoginUserCannotLoginException;
import com.goormitrip.goormitrip.user.exception.UserError;
import com.goormitrip.goormitrip.user.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackages = "com.goormitrip.goormitrip.user")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserExceptionHandler {

	@ExceptionHandler(EmailDuplicateException.class)
	public ResponseEntity<?> handleUserNotFound(final EmailDuplicateException ex) {
		log.warn("Email duplicate: {}", ex.getMessage());
		return ApiResponse.error(ex.getErrorCode());
	}

	@ExceptionHandler(PhoneNotVerifiedException.class)
	public ResponseEntity<?> handleUserPhoneNotVerified(final PhoneNotVerifiedException ex) {
		log.warn("Phone not verified: {}", ex.getMessage());
		return ApiResponse.error(ex.getErrorCode());
	}

	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<?> handleUserInvalidPassword(final InvalidPasswordException ex) {
		log.warn("Invalid password: {}", ex.getMessage());
		return ApiResponse.error(ex.getErrorCode());
	}

	@ExceptionHandler(RequiredTermsUncheckedException.class)
	public ResponseEntity<?> handleUserRequiredTermsUnchecked(final RequiredTermsUncheckedException ex) {
		log.warn("Required terms unchecked: {}", ex.getMessage());
		return ApiResponse.error(ex.getErrorCode());
	}

	@ExceptionHandler(InvalidEmailFormatException.class)
	public ResponseEntity<?> handleUserInvalidEmailFormat(final InvalidEmailFormatException ex) {
		log.warn("Invalid email format: {}", ex.getMessage());
		return ApiResponse.error(ex.getErrorCode());
	}

	@ExceptionHandler(InvalidPhoneException.class)
	public ResponseEntity<?> handleUserInvalidPhone(final InvalidPhoneException ex) {
		log.warn("Invalid phone number: {}", ex.getMessage());
		return ApiResponse.error(ex.getErrorCode());
	}

	@ExceptionHandler(PhoneVerificationFailedException.class)
	public ResponseEntity<?> handleUserPhoneVerificationFailed(final PhoneVerificationFailedException ex) {
		log.warn("Phone verification failed: {}", ex.getMessage());
		return ApiResponse.error(ex.getErrorCode());
	}

	@ExceptionHandler(LoginFailedException.class)
	public ResponseEntity<?> handleUserPhoneVerificationFailed(final LoginFailedException ex) {
		log.warn("Login failed: {}", ex.getMessage());
		return ApiResponse.error(ex.getErrorCode());
	}

	@ExceptionHandler(SocialLoginUserCannotLoginException.class)
	public ResponseEntity<?> handleSocialLoginUserCannotLogin(final SocialLoginUserCannotLoginException ex) {
		log.warn("Social login user cannot login: {}", ex.getMessage());
		return ApiResponse.error(ex.getErrorCode());
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handleBadCredentials(final BadCredentialsException ex) {
		log.warn("Bad credentials: {}", ex.getMessage());
		return ApiResponse.error(UserError.LOGIN_FAILED);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleUserNotFound(final UserNotFoundException ex) {
		log.warn("User not found: {}", ex.getMessage());
		return ApiResponse.error(ex.getErrorCode());
	}

	@ExceptionHandler(InvalidResetTokenException.class)
	public ResponseEntity<?> handleInvalidResetToken(final InvalidResetTokenException ex) {
		log.warn("Invalid reset token: {}", ex.getMessage());
		return ApiResponse.error(ex.getErrorCode());
	}

	@ExceptionHandler(MailSendFailedException.class)
	public ResponseEntity<?> handleMailSendFailed(final MailSendFailedException ex) {
		log.error("Mail send failed: {}", ex.getMessage());
		return ApiResponse.error(ex.getErrorCode());
	}
}
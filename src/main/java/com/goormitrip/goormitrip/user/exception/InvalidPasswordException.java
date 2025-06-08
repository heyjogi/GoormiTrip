package com.goormitrip.goormitrip.user.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class InvalidPasswordException extends BusinessException {
	public InvalidPasswordException() {
		super(UserError.INVALID_PASSWORD);
	}
}
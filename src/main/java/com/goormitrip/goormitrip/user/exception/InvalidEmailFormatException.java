package com.goormitrip.goormitrip.user.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class InvalidEmailFormatException extends BusinessException {
	public InvalidEmailFormatException() {
		super(UserError.INVALID_EMAIL_FORMAT);
	}
}
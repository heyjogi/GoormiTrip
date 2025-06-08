package com.goormitrip.goormitrip.user.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class InvalidPhoneException extends BusinessException {
	public InvalidPhoneException() {
		super(UserError.INVALID_PHONE);
	}
}
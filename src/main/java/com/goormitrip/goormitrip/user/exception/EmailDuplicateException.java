package com.goormitrip.goormitrip.user.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class EmailDuplicateException extends BusinessException {
	public EmailDuplicateException() {
		super(UserError.EMAIL_DUPLICATE);
	}
}

package com.goormitrip.goormitrip.user.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class LoginFailedException extends BusinessException {
	public LoginFailedException() {
		super(UserError.LOGIN_FAILED);
	}
}
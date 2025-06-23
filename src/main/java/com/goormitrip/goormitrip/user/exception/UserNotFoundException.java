package com.goormitrip.goormitrip.user.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class UserNotFoundException extends BusinessException {
	public UserNotFoundException() {
		super(UserError.USER_NOT_FOUND);
	}
}

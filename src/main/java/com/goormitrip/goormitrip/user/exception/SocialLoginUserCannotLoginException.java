package com.goormitrip.goormitrip.user.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class SocialLoginUserCannotLoginException extends BusinessException {

	public SocialLoginUserCannotLoginException() {
		super(UserError.SOCIAL_LOGIN_USER_CANNOT_LOGIN);
	}
}

package com.goormitrip.goormitrip.user.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class PhoneNotVerifiedException extends BusinessException {
	public PhoneNotVerifiedException() {
		super(UserError.PHONE_NOT_VERIFIED);
	}
}